#!/usr/bin/env python
'''
This module can be used to generate 'chains' of programming assignment submissions.

A chain is generated from a start submission, and lists out programming assignments that
are similar to each other, though each one is an improvement on the previous.

After the chain has been generated, this module can be used to give hints to a submission
to recommend ways the submission might improve. It does this by looking at a set
of features that future submissions in the chain have, and figuring out which is the 
most important feature to hint at.
'''

import argparse
import glob
import numpy as np
import os
import json
import pickle

class Chain():
    '''
    Data structure that holds chain links and contains data
    relevant to all links in the chain.
    '''
    NUM_CHANGES = 5
    JUMP_THRESHOLD = 0.05

    def __init__(self, source_dir, distances, style_scores, style_features, feature_names, score_names, libcall_linenums, libcall_dict, start_index, ast_distance_weight, style_score_weight):
        '''
        Initialize a chain starting at start_index.
        '''
        self.source_dir = source_dir
        self.dist_matrix = distances
        self.files = glob.glob(self.source_dir + '/*.rb')
        self.files.sort()
        self.style_scores = style_scores
        self.style_features = style_features
        self.feature_names = feature_names
        self.score_names = score_names
        self.libcall_linenums = libcall_linenums
        self.libcall_dict = libcall_dict
        
        self.weights_file = 'weights.np' #TODO: don't hard code
        self.feedback_file = 'feedback.np'
        
        self.create_chain(start_index, 0, 0)
        self.initialize_weights()
        
        self.ast_distance_weight = ast_distance_weight
        self.style_score_weight = style_score_weight
        
        
    def create_chain(self, start_index, slider1, slider2, feedback=None):
        '''
        Build a chain object from start_index.
        It will be built according to the parameters in the sliders.
        It will incorporate feedback into a list of feedback received so far.
        Then it will build the chain using all of the received feedback.
        feed back is a tuple of tuples.
        Inner tuples are of the form ('hint_name', index its a hint for, boolean if it was a useful hint).
        '''
        self.add_feedback(feedback)
        cl = ChainLink(start_index, None, self)
        self.head = cl
        self.length = 1
        while True:
            succ = cl.generate_successor()
            if not succ:
                break
            else:
                cl = succ
                self.length += 1
        self.tail= cl
        
    def add_feedback(self, feedback):
        '''
        Update weights if necessary according to the received feedback.
        Also add the feedback to list of all feedback received.
        '''
        self.update_weights(feedback)
        with open(self.feedback_file, 'a') as f:
            f.write(str(feedback))
            
        
    def update_weights(self, feedback):
        '''
        Change weights according to the feedback.
        This is a perceptron. Subject to change, of course!
        If the feedback is good, add in the feature vector associated with the hint to the weights
        If the feedback is bad, subtract off the feature vector associated with the hint from the weights
        '''
        if feedback is not None:
            for (hint_name, index_from, is_good) in feedback:
                cl = self.head 
                while (index_from > 0): #TODO: inefficient
                    cl = cl.next
                    index_from -= 1
                sparse_hint_vector = cl.extract_sparse_hint_features(np.where(self.feature_names==hint_name)[0][0])
                if is_good:
                    sparse_add_into(sparse_hint_vector, self.weights)
                else:
                    sparse_subtract_out_of(sparse_hint_vector, self.weights)
    
    def initialize_weights(self):
        '''
        If there is already a file with weights, use that.
        If there isn't, initialize weights manually.
        Then update weights according to feedback, if there is already a feedback file.
        '''
        if not os.path.isfile(self.weights_file):
            self.initialize_weights_manually()
            np.savetxt(self.weights_file, self.weights)
        else:
            self.weights = np.loadtxt(self.weights_file)
            if os.path.isfile(self.feedback_file):
                feedback = np.genfromtxt(self.feedback_file, dtype='str', delimiter='\n')
                if feedback.shape:
                    for i in xrange(feedback.shape):
                        feed = feedback[i]
                        self.add_feedback(feed)
        
    def initialize_weights_manually(self):
        '''
        These weight settings represent our best human guess
        as to what the weights should be.
        '''
        self.weights = np.zeros(self.style_features.shape[1] + 20) #TODO: only accounts for chains of up to length 20!
        self.weights[0:self.style_features.shape[1]] = 1 #Hint identity features
        self.weights[self.style_features.shape[1]] = -1000 #Does the hint appear in the current submission? (If so, don't suggest it)
        for i in xrange(0, 20): #Does the feature appear in next submission? In next next submission?
            self.weights[self.style_features.shape[1] + i] = (i+1)**3 #Higher weight if it appears later!
            
    def calls_that_produce(self, data_structure):
        '''
        Return a list of library calls that can produce this data structure,
        according to self.libcall_dict.
        '''
        #TODO: create a reversed version of libcall_dict for faster access
        calls = []
        for call in self.libcall_dict:
            if data_structure in self.libcall_dict[call]:
                calls.append(call)
        return calls
                
class ChainLink:
    '''Data structure that contains info relevant
    to only one link in the chain.
    '''
    
    def __init__(self, index, prev_link, chain):
        """
        Every chain link is associated with an 
        index from which you can learn everything about it
        (source code, features, score, etc.)
        
        A chain link must be created from another chain link, prev_link.
        """
        if prev_link:
            prev_link.next = self
        self.index = index
        self.chain = chain
        self.next = None
        self.positive_hint = None
        self.negative_hint = None
        with open(self.chain.files[self.index], 'r') as f:
            self.source_code = f.read()
        
    def generate_successor(self):
        '''
        Return a chain link corresponding to the next link in the chain.
        Recall that the next chain link should be the closest submission
        that is lower in some score.
        
        Returns None if there is no such submission.
        '''
        successor_index = self.find_closest_with_lower_value()
        if successor_index == -1:
            return None
        else:
            return ChainLink(successor_index, self, self.chain)
    
    def find_closest_with_lower_value(self):
        """
        Given distances between points and feature values for each point,
        return the index of the closest point with at least one lesser feature value.
        
        Return -1 if there is no such point.
        """
        #TODO: also return the why?
        invalid_features = np.empty(self.chain.style_scores.shape[0], dtype=bool)
        invalid_features[:] = False 
        for feature in xrange(self.chain.style_scores.shape[1]):
            invalid_features = np.logical_or(invalid_features, self.chain.style_scores[:,feature]>=self.chain.style_scores[self.index, feature])
            invalid_features = np.logical_or(invalid_features, np.abs(self.chain.style_scores[:,feature] - self.chain.style_scores[self.index, feature]) < self.chain.JUMP_THRESHOLD)
            maxed_dist_matrix = np.copy(self.chain.dist_matrix)
            maxed_dist_matrix.T[invalid_features] = float('inf')
            if np.min(maxed_dist_matrix[self.index,:]) == float('inf'):
                return -1
            else:
                index = np.argmin(maxed_dist_matrix[self.index,:])
                return index
            
    def extract_sparse_hint_features(self, i):
        '''
        Extract features for this hint.
        In order, features are: hint identity, 
        whether or not this hint appears in current submission,
        whether or not this hint appears in next submission,
        whether or not this hint appears in next next submission, and so on.
        '''
        sparse_vector = []
        sparse_vector.append(i) #Feature for hint identity
        offset = self.chain.style_features.shape[1]
        chain_link = self
        while (chain_link is not None):
            if self.chain.style_features[chain_link.index, i] > 0:
                sparse_vector.append(offset)
            chain_link = chain_link.next
            offset += 1
        return sparse_vector
            
    def get_positive_hint(self):
        if self.positive_hint == None:
            self.positive_hint, self.positive_hint_lines = self.generate_hint(False)
        return self.positive_hint, self.positive_hint_lines
            
    def get_negative_hint(self):
        if self.negative_hint == None:
            self.negative_hint, self.negative_hint_lines = self.generate_hint(True)
        return self.negative_hint, self.negative_hint_lines
            
    def generate_hint(self, is_not_hint):
        '''
        Return a list of hints about how to improve to get to the next chain link.
        Also return the line numbers that each hint is referencing.
        is_not_hint is a boolean that says whether this is a negative hint, i.e.
        a hint that suggests "Don't do this.", instead of "You should do this."
        '''
        if is_not_hint:
            invalid_hints = self.chain.style_features[self.index,:] <= self.chain.style_features[self.next.index, :]
        else:
            invalid_hints = self.chain.style_features[self.index,:] >= self.chain.style_features[self.next.index, :]
        selected_hints = []
        selected_scores = []
        for i in xrange(invalid_hints.shape[0]):
            if not invalid_hints[i]:
                sparse_hint_features = self.extract_sparse_hint_features(i)
                score = sparse_dot(sparse_hint_features, self.chain.weights)
                if score >= 0:
                    selected_hints.append(i)
                    selected_scores.append(score)
        sorted_selected_hints = [x for (y,x) in sorted(zip(selected_scores, selected_hints), key=lambda pair: -1*pair[0])]
        names = self.chain.feature_names[sorted_selected_hints]
        lines = []
        print self.chain.libcall_linenums[self.next.index]
        for name in names:
            name = name[2:-2]
            if name.startswith('->'):
                all_lines= []
                calls = self.chain.calls_that_produce(name[3:])
                for call in calls:
                    if is_not_hint and call in self.chain.libcall_linenums[self.index]:
                        all_lines += self.chain.libcall_linenums[self.index][call]
                    elif call in self.chain.libcall_linenums[self.next.index]:
                        all_lines += self.chain.libcall_linenums[self.next.index][call]
                lines.append(all_lines)
            elif is_not_hint:
                lines.append(self.chain.libcall_linenums[self.index][name])
            else:
                lines.append(self.chain.libcall_linenums[self.next.index][name])
        return names, lines
    
    
def interpret_list_of_hints(features, is_not_hint):
    '''
    Takes a set of features and constructs natural language hints about them.
    
    Disclaimer: This code assumes features have a very particular structure.
    It may need to be modified to be more robust.
    '''
    if is_not_hint:
        use_not = 'not '
        all_advice = 'You might also want to consider...\n'
    else:
        use_not = ''
        all_advice = 'To improve your style, you might want to consider...\n'
        
    create_new = False
    for feature in features:
        #TODO: does not generalize well!
        feature = feature[2:-2] #all features are in the form ['...']
        if feature[0:2] == '->':
            all_advice += '...' + use_not + 'using a method that produces ' + feature[3:]
            if feature[3:].endswith('s') or feature[3:].endswith('sh'):
                all_advice += 'es.\n'
            else:
                all_advice += 's.\n'
        elif feature[0] == '(':
            methods = ' '.join(feature.split('(')).split(')')

            method_advice = ''
            ngram = 0
            for method in methods:
                method = method.strip()
                if method == 'new':
                    create_new = True
                elif len(method) > 0:
                    method_advice += method
                    ngram += 1
            if ngram == 1:
                all_advice += '...' + use_not + 'using a call to ' + method_advice + '.\n'
            elif ngram > 1:
                all_advice += '...' + use_not + 'using a call to the following together: ' + method_advice + '.\n'
        else:
            all_advice += '...' + use_not + 'using a call to ' + feature + '.\n' 
    if create_new:
        all_advice += '...' + use_not + 'explicitly instantiating data structures.\n'
    return all_advice
        
def interpret_hint(feature):
    interpretation = ''
    feature = feature[2:-2] #all features are in the form ['...']
    if feature[0:2] == '->':
        interpretation = 'use a method that produces ' + feature[3:]
        if feature[3:].endswith('s') or feature[3:].endswith('sh'):
            interpretation += 'es.'
        else:
            interpretation += 's.'
    if feature[0] == '(':
        create_new = False
        methods = ' '.join(feature.split('(')).split(')')
        method_advice = ''
        ngram = 0
        for method in methods:
            method = method.strip()
            if method == 'new':
                create_new = True
            elif len(method) > 0:
                method_advice += method
                ngram += 1
        if ngram == 1:
            interpretation = 'use a call to ' + method_advice + '.'
        elif ngram > 1:
            interpretation = 'use a call to the following together: ' + method_advice + '.' 
        if create_new:
            interpretation = 'explicitly instantiate data structures.'
    else:
        interpretation = 'use a call to ' + feature + '.'
    return interpretation

def sparse_dot(sparse_vector, vector):
    '''
    Compute a dot product between a sparse vector and a regular vector.
    The sparse vector is a list of true indices in a binary vector.
    '''
    return np.sum(vector[sparse_vector])

def sparse_add_into(sparse_vector, vector):
    '''
    Modify vector to be the sum of vector and a sparse vector.
    The sparse vector is a list of true indices of a binary vector.
    '''
    vector[sparse_vector] += 1
    
def sparse_subtract_out_of(sparse_vector, vector):
    '''
    Modify vector to be the vector - a sparse vector.
    The sparse vector is a list of true indices of a binary vector.
    '''
    vector[sparse_vector] -= 1

def generate_chain(start_index, ast_distance_weight, style_score_weight, home_dir,
                   feedback=None, 
                   style_scores='data/feature/inherent_style_features.np', 
                   style_features='data/feature/instrumental_style_features.np', 
                   score_names='data/feature/inherent_style_names.np', 
                   feature_names='data/feature/instrumental_style_names.np', 
                   source='data/feature/method_source', 
                   distances='data/gen/ast_dist_matrix.np',
                   libcall_linenums='featurization/libcalls_and_linenums.json',
                   libcall_dict='util/lib_call_dict.pkl'):


    distances = np.loadtxt(home_dir + distances)
    style_scores = np.loadtxt(home_dir + style_scores)
    feature_names = np.genfromtxt(home_dir + feature_names, dtype='str', delimiter='\n')
    score_names = np.genfromtxt(home_dir + score_names, dtype='str', delimiter='\n')
    if len(style_scores.shape)==1:
        style_scores = style_scores[:, np.newaxis]
    style_features = np.loadtxt(home_dir + style_features)
    with open(libcall_linenums, 'r') as json_data:
        libcall_linenums = json.load(json_data)
    with open(libcall_dict, 'r') as f:
        libcall_dict = pickle.load(f)
    c = Chain(home_dir+source, distances, style_scores, style_features, feature_names, score_names, libcall_linenums, libcall_dict, start_index, ast_distance_weight, style_score_weight)
    return c

def main():
    parser = argparse.ArgumentParser(description='Find paths from submissions with high style scores to submissions with low ones.')
    parser.add_argument('start_index', type=int, help='Index of the start submission in the index file')
    parser.add_argument('style_scores', nargs='?', default='data/feature/inherent_style_features.np', help='Location of file that contains style score vectors')
    parser.add_argument('style_features', nargs='?', default='data/feature/instrumental_style_features.np', help='Location of file that contains explanatory style features.')
    parser.add_argument('score_names', nargs='?', default='data/feature/inherent_style_names.np', help='Location of file that contains names for each style score feature')
    parser.add_argument('feature_names', nargs='?', default='data/feature/instrumental_style_names.np', help='Location of file that contains names for each feature')
    parser.add_argument('source', nargs='?', default='data/feature/method_source', help='Source folder of submissions')
    parser.add_argument('distances', nargs='?', default='data/gen/ast_dist_matrix.np', help='Location of file that contains distances between submissions')
    parser.add_argument('libcall_linenums', nargs='?', default='featurization/libcalls_and_linenums.json', help='Location of file that contains line numbers associated with each library call')
    parser.add_argument('libcall_dict', nargs='?', default='util/lib_call_dict.pkl', help='Location of file that contains a mapping from lib calls to object types they produce')

    args = parser.parse_args()
    start_index = args.start_index
    source = args.source
    distances = np.loadtxt(args.distances)
    style_scores = np.loadtxt(args.style_scores)
    feature_names = np.genfromtxt(args.feature_names, dtype='str', delimiter='\n')
    score_names = np.genfromtxt(args.score_names, dtype='str', delimiter='\n')
    with open(args.libcall_linenums, 'r') as json_data:
        libcall_linenums = json.load(json_data)
    if len(style_scores.shape)==1:
        style_scores = style_scores[:, np.newaxis]
    style_features = np.loadtxt(args.style_features)
    with open(args.libcall_dict , 'r') as pickle_data:
        libcall_dict = pickle.load(pickle_data)
    #c = generate_chain(start_index, .05, 0, None, style_scores, style_features, score_names, feature_names, source, distances, libcall_linenums, libcall_dict)
    c = Chain(source, distances, style_scores, style_features, feature_names, score_names, libcall_linenums, libcall_dict, start_index, 0, .05)
    cl = c.head
    i = 0
    while cl:
        print '======= ' + str(i) + ' ======='
        print cl.index
        print cl.source_code
        if cl.next:
            print ''
            pos_hints, pos_lines = cl.get_positive_hint()
            neg_hints, neg_lines = cl.get_negative_hint()
            print interpret_list_of_hints(pos_hints, False)
            print pos_lines
            print interpret_list_of_hints(neg_hints, True)
            print neg_lines
        cl = cl.next
        i+=1
    
    
if __name__ == '__main__':
    main()