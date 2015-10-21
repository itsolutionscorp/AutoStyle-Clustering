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
import pickle
import re
import csv
from collections import OrderedDict

NUM_STRUCTURAL_FEATURES = 8

class Chain():
    '''
    Data structure that holds chain links and contains data
    relevant to all links in the chain.
    '''

    def __init__(self, source_dir, distances, style_scores, style_features, feature_names, score_names, weights_file, libcall_dict, start_index, ast_distance_weight, style_score_weight, feedback, old_chain, line_num_matrix, clusters, manual_hints, max_hints=3):
        '''
        Initialize a chain starting at start_index.
        This also initializes all of the constants.
        '''
        self.source_dir = source_dir
        self.dist_matrix = distances
        self.files = natural_sort(glob.glob(self.source_dir + '/*'))
        self.style_scores = style_scores
        self.style_features = style_features
        self.feature_names = feature_names
        self.score_names = score_names
        self.libcall_dict = libcall_dict
        self.line_num_matrix = line_num_matrix
        
        self.weights_file = weights_file
        
        self.ast_distance_weight = ast_distance_weight
        self.style_score_weight = style_score_weight
        self.max_jump_threshold = self.style_score_weight * 10  # TODO: undo this
        self.min_jump_threshold = 1
        
        self.num_hints = max_hints 
        self.positive_feedback_scale = 0
        self.negative_feedback_scale = -10000
        self.num_structural_features = NUM_STRUCTURAL_FEATURES

        self.clusters = clusters
        self.manual_hints = manual_hints
        self.initialize_weights()
        if feedback:
            self.update_weights(feedback, old_chain)
        self.grow_chain(start_index)
        
    def grow_chain(self, start_index):
        '''
        Build a chain object from start_index.
        '''
        cl = ChainLink(start_index, None, self, 0)
        self.head = cl
        self.length = 1
        while True:
            threshold = self.max_jump_threshold
            succ = cl.generate_successor(threshold)
            while not succ and threshold > self.min_jump_threshold:
                threshold -= 1
                succ = cl.generate_successor(threshold)
            if not succ:
                break
            else:
                cl = succ
                self.length += 1
        self.tail= cl
        
    def update_weights(self, feedback, old_chain):
        '''
        Change weights according to the feedback.
        This is a perceptron, subject to change.
        If the feedback is good, add in the feature vector associated with the hint to the weights
        If the feedback is bad, subtract off the feature vector associated with the hint from the weights
        '''
        if not feedback or not old_chain:
            return
        for (hint_name, index_from, is_bad, sign) in feedback:
            cl = old_chain.head 
            while (index_from > 0):
                cl = cl.next
                index_from -= 1
            sparse_hint_vector = cl.extract_sparse_hint_features(np.where(self.feature_names==hint_name)[0][0])[0] #TODO 0 only takes hint identity
            if is_bad==0:
                sparse_add_into(sparse_hint_vector, self.weights, self.positive_feedback_scale)
            else: #is_bad==1
                sparse_add_into(sparse_hint_vector, self.weights, self.negative_feedback_scale)
        np.savetxt(self.weights_file, self.weights)
    
    def initialize_weights(self):
        '''
        If there is already a file with weights, use that.
        If there isn't, initialize weights manually.
        '''
        if not os.path.isfile(self.weights_file):
            self.initialize_weights_manually()
            np.savetxt(self.weights_file, self.weights)
        else:
            self.weights = np.loadtxt(self.weights_file)
        
    def initialize_weights_manually(self):
        '''
        These weight settings represent our best human guess
        as to what the weights should be.
        '''
        num_features = self.style_features.shape[1]
        self.weights = np.zeros(num_features + 20)  # TODO: only accounts for chains of up to length 20!
        self.weights[0:num_features] = 1  # Hint identity features
        self.weights[num_features - self.num_structural_features:num_features] = 1000  # Make structural features take priority over others
        self.weights[num_features] = -1000  # Does the hint appear in the current submission? (If so, don't suggest it)
        for i in xrange(0, 20): #Does the feature appear in next submission? In next next submission?
            self.weights[self.style_features.shape[1] + i] = (i+1)**3 #Higher weight if it appears later!
            
    def calls_that_produce(self, data_structure):
        '''
        Return a list of library calls that can produce this data structure,
        according to self.libcall_dict.
        '''
        calls = []
        for call in self.libcall_dict:
            if data_structure in self.libcall_dict[call]:
                calls.append(call)
        return calls
                
class ChainLink:
    '''
    Data structure that contains info relevant
    to only one link in the chain.
    '''
    
    def __init__(self, index, prev_link, chain, pos_in_chain = 0):
        '''
        Every chain link is associated with an 
        index from which you can learn everything about it
        (source code, features, score, etc.)
        
        A chain link must be created from another chain link, prev_link.
        '''
        if prev_link:
            prev_link.next = self
        self.index = index
        self.chain = chain
        self.flog_score = self.chain.style_scores[self.index, 0]  # TODO: flog score isn't necessarily at position 0...
        self.next = None
        self.positive_hint = None
        self.negative_hint = None
        self.position_in_chain = pos_in_chain
        with open(self.chain.files[self.index], 'r') as f:
            self.source_code = f.read()
        
    def generate_successor(self, threshold):
        '''
        Return a chain link corresponding to the next link in the chain.
        The next chain link should be the closest submission
        that is lower in some score.
        
        Returns None if there is no such submission.
        '''
        successor_index = self.find_closest_with_lower_value(threshold)
        if successor_index == -1:
            return None
        else:
            return ChainLink(successor_index, self, self.chain, self.position_in_chain+1)
    
    def find_closest_with_lower_value(self, threshold):
        """
        Given distances between points and feature values for each point,
        return the index of the closest point with at least one better score.
        That score must be better by at least threshold.
        Return -1 if there is no such point.
        """
        invalid_features = np.empty(self.chain.style_scores.shape[0], dtype=bool)
        invalid_features[:] = False 
        for feature in xrange(self.chain.style_scores.shape[1]):
            # invalid if its score is worse than our own 
            invalid_features = np.logical_or(invalid_features, 
                                             self.chain.style_scores[:,feature] >= self.chain.style_scores[self.index, feature])
            # invalid if its score differs from our own by less than jump threshold
            invalid_features = np.logical_or(invalid_features, 
                                             np.abs(self.chain.style_scores[:, feature] - self.chain.style_scores[self.index, feature]) < threshold)
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
            self.positive_hint, self.positive_hint_lines, self.positive_hint_locations = self.generate_hint(False)
        return self.positive_hint, self.positive_hint_lines, self.positive_hint_locations
            
    def get_negative_hint(self):
        if self.negative_hint == None:
            self.negative_hint, self.negative_hint_lines, self.negative_hint_locations = self.generate_hint(True)
        return self.negative_hint, self.negative_hint_lines, self.negative_hint_locations

    def get_approach_hint(self):
        clusters = self.chain.clusters
        closest_neighbors = np.argmin(self.chain.dist_matrix[self.index])[:5] # TODO make it a parameter, 5 is the k for kNN algorithm
        ind = np.argsort(clusters[:, 0])
        sorted_clusters = clusters[ind, :]
        cluster = np.argmax(np.bincount(sorted_clusters[closest_neighbors, 1]))
        return self.chain.manual_hints[cluster][2] # TODO the third column stores hints

    def get_sorted_hints(self, next_link, num_hints, is_not_hint, must_be_structural, used_hints):
        '''
        Return the best possible hints sorted by score.
        Also return what submission they appear in.
        A hint is only possible if it corresponds to a feature
        that doesn't appear in this chain link but does appear
        in the next one, or vice-versa for not hints.
        
        must_be_structural limits this call to only returning hints
        that count as structural, which are hardcoded.
        
        Will not return any hints from used_hints, since these are
        considered already used.
        '''
        if is_not_hint:
            invalid_hints = self.chain.style_features[self.index, :] <= self.chain.style_features[next_link.index, :]
        else:
            invalid_hints = self.chain.style_features[self.index, :] >= self.chain.style_features[next_link.index, :]
            
        if must_be_structural:
            num_features = self.chain.style_features.shape[1]
            structural = np.empty(num_features, dtype=bool)
            structural[:] = True
            structural[num_features - self.chain.num_structural_features:num_features] = False
            invalid_hints = np.logical_or(invalid_hints, structural)
            
        selected_hints = []
        selected_scores = []
        for i in xrange(invalid_hints.shape[0]):
            if not invalid_hints[i]:
                sparse_hint_features = self.extract_sparse_hint_features(i)
                score = sparse_dot(sparse_hint_features, self.chain.weights)
                if score >= 0:
                    selected_hints.append(i)
                    selected_scores.append(score)
        
        sorted_hints = [x for (_, x) in sorted(zip(selected_scores, selected_hints), key=lambda pair:-1 * pair[0])]
        sorted_hints = [x for x in sorted_hints if x not in used_hints]
        sorted_hints = list(OrderedDict.fromkeys(sorted_hints))
        if (is_not_hint):
            locations = [(self.index, self.position_in_chain) for _ in sorted_hints]
        else:
            locations = [(next_link.index, next_link.position_in_chain) for _ in sorted_hints]
        unused_hints = self.chain.num_hints - len(sorted_hints)
        if unused_hints > 0 and next_link.next is not None:
            additional_sorted_hints, additional_locations = self.get_sorted_hints(next_link.next, unused_hints, is_not_hint, must_be_structural, sorted_hints)
            sorted_hints += additional_sorted_hints
            locations += additional_locations
        return remove_duplicates(sorted_hints[:num_hints], locations)

    def generate_hint(self, is_not_hint):
        '''
        Return a list of hints about how to improve to get to the next chain link.
        Also return the line numbers that each hint is referencing.
        Also return which chain link the hints correspond to.
        is_not_hint is a boolean that says whether this is a negative hint, i.e.
        a hint that suggests "Don't do this.", instead of "You should do this."
        '''
        
        sorted_hints, locations = self.get_sorted_hints(self.next, self.chain.num_hints, is_not_hint, True, [])
        unused_hints = self.chain.num_hints - len(sorted_hints)
        if unused_hints > 0:
            nonstructural_hints, nonstructural_locations = self.get_sorted_hints(self.next, unused_hints, is_not_hint, False, sorted_hints)
            sorted_hints += nonstructural_hints
            locations += nonstructural_locations
            sorted_hints, locations = remove_duplicates(sorted_hints, locations)
        names = self.chain.feature_names[sorted_hints]
        indices = [i[0] for i in locations]
        locations = [i[1] for i in locations]
        lines = [self.chain.line_num_matrix[x, y] for x, y in zip(indices, sorted_hints)]
        return names, lines, locations
    
def interpret_list_of_hints(features, is_not_hint):
    '''
    Take a set of features and constructs natural language hints about them.
    
    Disclaimer: This code assumes features have a very particular structure.
    It may need to be modified to be more robust.
    '''
    if is_not_hint:
        use_not = 'not '
        all_advice = 'You might also want to consider...\n'
    else:
        use_not = ''
        all_advice = 'To improve your style, you might want to consider...\n'
    # is there a better way to do this -- to make it more general?
    python_specific_constructs = {"BinOp": "a binary operation", "Lambda": "a lambda function", "IfExp": "an if expression", "ListComp": "list comprehension" , "DictComp": "dictionary comprehension", "GeneratorExp": "a generator expression", "SetComp": "set comprehension", "AugAssign": "an augmented assignment", "TryFinally": "try-finally construct", "ClassDef":"a class definition"}
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
        elif feature == 'duplicate_treegrams':
            if is_not_hint:
                all_advice += '...' + 'restructuring your program to eliminate redundant code' + '.\n'
        elif feature == 'conditional':
            all_advice += '...' + 'restructuring your function to ' + use_not + 'use a conditional' + '.\n'
        elif feature == 'nested conditionals':
            all_advice += '...' + 'restructuring your function to ' + use_not + 'use nested conditionals' + '.\n'
        elif feature == 'explicit iteration':
            all_advice += '...' + 'restructuring your function to ' + use_not + 'use explicit iteration' + '.\n'
        elif feature == 'nested explicit iteration':
            all_advice += '...' + 'restructuring your function to ' + use_not + 'use nested iteration' + '.\n'
        elif feature == 'sequential conditional':
            all_advice += '...' + 'restructuring your function to ' + use_not + 'use sequential conditional blocks' + '.\n'
        elif feature == 'sequential iteration':
            all_advice += '...' + 'restructuring your function to ' + use_not + 'use sequential iteration blocks' + '.\n'
        elif feature == 'recursion':
            all_advice += '...' + 'restructuring your function to ' + use_not + 'recursion' + '.\n'
        elif feature in ["Continue", "AugAssign", "Exec", "Global", "Yield", "In", "Nonlocal", "YieldFrom", "With"]:
            all_advice += '...' + use_not + 'using the ' + feature.lower() + ' operator.\n'
        elif feature in python_specific_constructs:
            all_advice += '...' + use_not + 'using '+ python_specific_constructs[feature] + '.\n'
        else:
            all_advice += '...' + use_not + 'using a call to ' + feature + '.\n' 
    if create_new:
        all_advice += '...' + use_not + 'explicitly instantiating data structures.\n'
    return all_advice

def remove_duplicates(l1, l2):
    '''
    Return l1 with all of its duplicate items removed.
    Also return l2 with items removed from the same indices
    '''
    seen_items = set()
    new_l1 = []
    new_l2 = []
    for (i, item) in enumerate(l1):
        if not item in seen_items:
            seen_items.add(item)
            new_l1.append(item)
            new_l2.append(l2[i])
    return new_l1, new_l2

def integer_val(x):
    '''
    Return the integer part of an alphanumeric string x
    '''
    return int(x.split("/")[-1].strip(".").strip("./"))

def natural_sort(l):
    '''
    Sorts strings of numbers like numbers rather than like strings.
    ''' 
    convert = lambda text: int(text) if text.isdigit() else text.lower() 
    alphanum_key = lambda key: [ convert(c) for c in re.split('([0-9]+)', key) ] 
    return sorted(l, key = alphanum_key)

def sparse_dot(sparse_vector, vector):
    '''
    Compute a dot product between a sparse vector and a regular vector.
    The sparse vector is a list of true indices in a binary vector.
    '''
    return np.sum(vector[sparse_vector])

def sparse_add_into(sparse_vector, vector, scale):
    '''
    Modify vector to be the sum of vector and a sparse vector.
    The sparse vector is a list of true indices of a binary vector.
    '''
    vector[sparse_vector] += scale

def generate_chain(start_index, max_hints, style_score_weight, home_dir = "./",
                   feedback=None, old_chain=None, data_dir='data/', weights_file='weights.np',
                   libcall_dict='util/lib_call_dict.pkl', language="ruby"):
    '''
    Create a new chain object. This is the interface with the web app.
    Disclaimer: Assumes data_dir has a particular structure. 
    '''
    home_dir = home_dir.rstrip("/") + "/"
    data_dir = data_dir.rstrip("/") + "/"
    feature_dir = home_dir + data_dir + 'feature/'
    source_dir = home_dir + data_dir + 'src/'
    distances = np.loadtxt(home_dir + data_dir + 'gen/ast_dist_matrix.np')
    clusters = np.loadtxt(home_dir + data_dir + 'gen/coordinates.csv', delimiter=",", skiprows = 1, usecols = [2, 3])
    manual_hints = []
    with open(feature_dir + "/manual_hints.csv", "r") as f:
        for line in f.readlines()[1:]:
            manual_hints.append(line.split(","))
    style_scores = np.loadtxt(feature_dir + 'style_scores.np', ndmin=2)
    style_features = np.loadtxt(feature_dir + 'style_features.np', ndmin=2)
    feature_names = np.genfromtxt(feature_dir + 'style_features_names.np', dtype='str', delimiter='\n')
    score_names = np.genfromtxt(feature_dir + 'style_scores_names.np', dtype='str', delimiter='\n')
    line_num_matrix = np.loadtxt(feature_dir + 'feature_line_nums.np', ndmin=2)
    weights_file = home_dir+data_dir + 'gen/weights.np' 
    ast_distance_weight=0.05
    if language == "ruby":
        if len(style_scores.shape)==1:
            style_scores = style_scores[:, np.newaxis]
        with open(home_dir + libcall_dict, 'r') as f:
            libcall_dict = pickle.load(f)

    c = Chain(source_dir, distances, style_scores, style_features, feature_names, score_names, weights_file, libcall_dict, start_index, ast_distance_weight, style_score_weight, feedback, old_chain, line_num_matrix, clusters, manual_hints, max_hints=max_hints)
    return c

def unicode_to_str(input_u):
    if isinstance(input_u, dict):
        return {unicode_to_str(key):unicode_to_str(value) for key,value in input_u.iteritems()}
    elif isinstance(input_u, list):
        return [unicode_to_str(element) for element in input_u]
    elif isinstance(input_u, unicode):
        return input_u.encode('utf-8')
    else:
        return input_u

def main():
    '''
    Runs a text version (rather than a gui version)
    of the code. Cannot receive feedback or adjust
    parameters in this mode.
    
    To use, run from the top level of the repo. Just run
    
        python visualization/style_chain.py [num]
        
    All other parameters should have their default values working.
    '''
    parser = argparse.ArgumentParser(description='Find paths from submissions with high style scores to submissions with low ones.')
    parser.add_argument('start_index', type=int, help='Index of the start submission in the index file')
    parser.add_argument('data_dir', nargs='?', default='data/', help='Location of directory that contains features, method source, and asts.')
    parser.add_argument('libcall_dict', nargs='?', default='util/lib_call_dict.pkl', help='Location of file that contains a mapping from lib calls to object types they produce')

    args = parser.parse_args()
    c = generate_chain(args.start_index, 3, 2, data_dir=args.data_dir, libcall_dict=args.libcall_dict)
    cl = c.head
    i = 0
    source_files = natural_sort(glob.glob(args.data_dir + '/src/*'))
    while cl:
        print '======= ' + str(i) + ' ======='
        print cl.index
        print source_files[cl.index]
        print cl.source_code
        if cl.next:
            print ''
            pos_hints, pos_lines, pos_locations = cl.get_positive_hint()
            neg_hints, neg_lines, neg_locations = cl.get_negative_hint()
            print interpret_list_of_hints(pos_hints, False)
            print 'Line nums:' + str(pos_lines)
            print 'Location:' + str(pos_locations)
            print interpret_list_of_hints(neg_hints, True)
            print 'Line nums:' + str(neg_lines)
            print 'Location:' + str(neg_locations)
        cl = cl.next
        i+=1
    
if __name__ == '__main__':
    main()
