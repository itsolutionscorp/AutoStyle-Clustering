#!/usr/bin/env python

import argparse
import glob
import numpy as np

class Chain():
    NUM_CHANGES = 15
    JUMP_THRESHOLD = 0.05

    def __init__(self, source_dir, distances, style_scores, style_features, feature_names, score_names):
        self.source_dir = source_dir
        self.dist_matrix = distances
        self.files = glob.glob(self.source_dir + '/*.rb')
        self.files.sort()
        self.style_scores = style_scores
        self.style_features = style_features
        self.feature_names = feature_names
        self.score_names = score_names
        self.common_changes = set()
        
    def interpret(self, change, chain, index):
        return change
    
    def closest_submission_with_lower_values(self, submission_index):
        '''Given distances between points and feature values for each point,
        return the index of the closest point with at least one lesser feature value.
        Also return the index of the feature itself.
        Return -1 if there is no such point.
        '''
        invalid_features = np.empty(self.style_scores.shape[0],dtype=bool)
        invalid_features[:] = False 
        for feature in xrange(self.style_scores.shape[1]):
            invalid_features = np.logical_or(invalid_features, self.style_scores[:,feature]>=self.style_scores[submission_index, feature])
            invalid_features = np.logical_or(invalid_features, np.abs(self.style_scores[:,feature] - self.style_scores[submission_index, feature]) < self.JUMP_THRESHOLD)
            maxed_dist_matrix = np.copy(self.dist_matrix)
            maxed_dist_matrix.T[invalid_features] = float('inf')
            if np.min(maxed_dist_matrix[submission_index,:]) == float('inf'):
                return -1, None
            else:
                index = np.argmin(maxed_dist_matrix[submission_index,:])
                feature_index = np.argmin(self.style_scores[index, :])
                feature_change = self.style_scores[index, feature_index] - self.style_scores[submission_index, feature_index]
                return index, (self.score_names[feature_index], feature_change)
        
    def calculate_common_changes(self, k):
        '''Calculate the changes most likely to appear in a chain link.
        These are more associated with drops in style scores.
        '''
        changes_count = {}
        for i in xrange(len(self.files)):
            successor_i, score_change = self.closest_submission_with_lower_values(i)
            changes, indices= self.biggest_changes(i, successor_i)
            for change in changes:
                if change not in changes_count:
                    changes_count[change] = 0
                changes_count[change] += 1
        sorted_changes = sorted(changes_count, key = changes_count.get, reverse=True)
        self.common_changes = set(sorted_changes[0:k])
        
    def give_best_hint(self, submission_index, submission_chain):
        #TODO: finish
        chain_matrix = self.get_chain_matrix(submission_index, submission_chain)
        total_features = np.sum(chain_matrix, 0) #Indicates some notion of importance
        
        changes, indices = self.biggest_changes(submission_index, submission_chain[1])

        weighted_changes = {}
        for i, change in enumerate(changes):
            weighted_changes[change] = changes[change]*total_features[indices[i]]
            
        v = list(weighted_changes.values())
        k = list(weighted_changes.keys())
        return k[v.index(max(v))]
                    
    def get_chain_matrix(self, index, chain):
        matrix = np.zeros((len(chain), self.style_features.shape[1]))
        for i, chainlink in enumerate(chain):
            matrix[i, :] = i*(self.style_features[chainlink, :] - self.style_features[index, :])
        return matrix
        
        
    def give_hints(self, submission_index, submission_chain):
        successor_index, score_change = self.closest_submission_with_lower_values(submission_index)
        changes, indices = self.biggest_changes(submission_index, successor_index)
        positive_changes = set()
        negative_changes = set()
        for change in changes:
            if changes[change] < 0:
                negative_changes.add(change)
            else:
                positive_changes.add(change)
        feedback = 'You might be able to improve your solution in terms of ' + score_change[0] +'.\n'
        feedback += 'A similar solution that improves in this area might use:\n'
        for change in positive_changes:
            feedback += self.interpret(change, submission_chain, submission_index) + '\n'
        feedback += '\nA similar solution that improves in this area might not use:\n'
        for change in negative_changes:
            feedback += self.interpret(change, submission_chain, submission_index) + '\n'
        return feedback
    
    def improvement_sequence(self, start_submission):
        current_submission = start_submission
        while True:
            new_submission = current_submission.closest_successor_with_lower_value(self.style_scores)
            if not new_submission:
                return current_submission.path, current_submission.index_path, current_submission.source_code_path, current_submission.changes_path, current_submission.score_changes_path
            current_submission = new_submission
            
    def biggest_changes(self, i1, i2):
        if i1 == -1:
            return {}, []
        features1 = self.style_features[i1,:]
        features2 = self.style_features[i2,:]
        change_values = features2 - features1
        deltas = np.absolute(change_values)
        indices = np.argsort(deltas)[-1*Chain.NUM_CHANGES:][::-1] #Getting maximum deltas
        d = {}
        for i in indices:
            d[self.feature_names[i]] = change_values[i]
        return d, indices
                
class ChainLink:
    
    def __init__(self, parent_state, index, score_change, chain_follower):
        
        self.chain_follower = chain_follower
        self.index = index;
        self.file_name = self.chain_follower.files[self.index]
                    
        if not parent_state:
            self.parent_index= -1
            self.path = []
            self.source_code_path = []
            self.changes_path = []
            self.score_changes_path = []
            self.index_path = []
        else:
            self.parent_index = parent_state.index
            self.path = parent_state.path[:]
            self.changes_path = parent_state.changes_path[:]
            self.source_code_path = parent_state.source_code_path[:]
            self.score_changes_path = parent_state.score_changes_path[:]
            self.index_path = parent_state.index_path[:]
        if score_change:
            self.score_changes_path.append(score_change)
        else:
            self.score_changes_path.append(('', 0))
        self.path.append(self.chain_follower.files[index])
        self.index_path.append(self.index)
        change, indices = self.chain_follower.biggest_changes(self.parent_index, self.index)
        self.changes_path.append(change)
        with open(self.file_name) as f:
            source_code = f.read()
        self.source_code_path.append(source_code)
    
    def closest_successor_with_lower_value(self, feature_list):
        next_index, score_change = self.chain_follower.closest_submission_with_lower_values(self.index)
        if next_index < 0:
            return None
        else:
            return ChainLink(self, next_index, score_change, self.chain_follower)

def main():
    parser = argparse.ArgumentParser(description='Find paths from submissions with high style scores to submissions with low ones.')
    parser.add_argument('start_index', type=int, help='Index of the start submission in the index file')
    parser.add_argument('style_scores', nargs='?', default='data/feature/inherent_style_features.np', help='Location of file that contains style score vectors')
    parser.add_argument('style_features', nargs='?', default='data/feature/instrumental_style_features.np', help='Location of file that contains explanatory style features.')
    parser.add_argument('score_names', nargs='?', default='data/feature/inherent_style_names.np', help='Location of file that contains names for each style score feature')
    parser.add_argument('feature_names', nargs='?', default='data/feature/instrumental_style_names.np', help='Location of file that contains names for each feature')
    parser.add_argument('source', nargs='?', default='data/feature/method_source', help='Source folder of submissions')
    parser.add_argument('distances', nargs='?', default='data/gen/ast_dist_matrix.np', help='Location of file that contains distances between submissions')

    args = parser.parse_args()
    start_index = args.start_index
    source = args.source
    distances = np.loadtxt(args.distances)
    style_scores = np.loadtxt(args.style_scores)
    feature_names = np.genfromtxt(args.feature_names, dtype='str', delimiter='\n')
    score_names = np.genfromtxt(args.score_names, dtype='str', delimiter='\n')
    if len(style_scores.shape)==1:
        style_scores = style_scores[:, np.newaxis]
    style_features = np.loadtxt(args.style_features)
    
    cf = Chain(source, distances, style_scores, style_features, feature_names, score_names)
    initial_state = ChainLink(None, start_index, None, cf)
    
    submissions, submission_indices, source_codes, feature_changes, score_changes = cf.improvement_sequence(initial_state)
    for i, (sub, src, c, scores) in enumerate(zip(submissions, source_codes, feature_changes, score_changes)):
        print '======= ' + str(i) + ' ======='
        print 'Score fell in ' + scores[0] + ' by ' + str(-1*scores[1]) + '!\n'
        #for change in sorted(c, key=c.get):
            #if change in cf.common_changes:
                #print '*' + change + ': ' + str(c[change]) 
            #else:
                #print change + ': ' + str(c[change])
        print ''
        print sub
        print ''
        print src
        print ''
        if i < len(submission_indices)-1:
            print 'Hint: use ' + cf.give_best_hint(submission_indices[i], submission_indices[i:])  
        
    #print cf.give_hints(start_index, submission_indices)
        

if __name__ == '__main__':
    main()