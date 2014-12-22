#!/usr/bin/env python

from heapq import *
import argparse
import glob
import numpy as np
from visualization.ParallelCoordinates import parallel_coordinates

def closest_submission_with_lower_values(submission_index, dist_matrix, feature_list):
    '''Given distances between points and a feature value for each point,
    return the index of the closest point with a lesser feature value.
    '''
    invalid_features = np.empty_like(feature_list[0],dtype=bool)
    invalid_features[:] = False 
    for feature in feature_list:
        invalid_features = np.logical_or(invalid_features, feature>=feature[submission_index])
    maxed_dist_matrix = np.copy(dist_matrix)
    maxed_dist_matrix.T[invalid_features] = float('inf')
    if np.min(maxed_dist_matrix[submission_index,:]) == float('inf'):
        return -1
    else:
        return np.argmin(maxed_dist_matrix[submission_index,:])

class Chain():
    BEAM_SIZE = 500
    FLOG_THRESHOLD = 7
    FLOG_JUMP_LOWERBOUND = 1
    FLOG_JUMP_UPPERBOUND = 3
    AST_JUMP_UPPERBOUND = 50
    
    def __init__(self, source_dir):
        self.source_dir = source_dir
        self.dist_matrix = np.loadtxt('data/gen/ast_dist_matrix.np')
        self.files = glob.glob(self.source_dir + '/*.rb')
        self.files.sort()
        self.flog_scores = np.loadtxt('data/feature/flog_feature.np')
        self.factor_loadings = np.loadtxt('nmf/transformed_data.np')
        idx = np.argsort(self.flog_scores)
        goal_indices = idx[np.sort(self.flog_scores)<Chain.FLOG_THRESHOLD]
        self.goal_solutions = []
        for i in goal_indices:
            self.goal_solutions.append(self.files[i])

    def find_path(self, start_submission):
        fringe = []
        heappush(fringe, start_submission)
        while(fringe):
            current_submission = heappop(fringe)
            if current_submission.file_name in self.goal_solutions:
                return current_submission.path, current_submission.source_code_path, current_submission.factor_path
            for successor in current_submission.successors():
                if len(fringe) > Chain.BEAM_SIZE:
                    heappushpop(fringe, successor)
                else:
                    heappush(fringe, successor)
        return 'no path found', 'no path found', 'no path found'
    
    def closest_improvement_sequence(self, start_submission, feature_list):
        current_submission = start_submission
        while True:
            new_submission = current_submission.closest_successor_with_lower_value(feature_list)
            if not new_submission:
                return current_submission.path, current_submission.source_code_path, current_submission.factor_path
            current_submission = new_submission
                
class ChainLink:
    
    def __init__(self, path_finder, index, parent_index, path, source_code_path, factor_path):
        self.path_finder = path_finder
        self.index = index;
        self.file_name = self.path_finder.files[self.index]
        self.parent_index = parent_index
        self.value = self.path_finder.dist_matrix[self.parent_index, self.index]
        self.path = path + ' ' + self.path_finder.files[index]
        self.factors = self.path_finder.factor_loadings[index,:]
        if factor_path!=None:
            self.factor_path = factor_path[:, :]
            self.factor_path = np.append(self.factor_path, np.reshape(self.factors, (self.factors.size,1)), 1)
        else:
            self.factor_path = np.reshape(self.factors, (self.factors.shape[0], 1))
        with open(self.file_name) as f:
            source_code = f.read()
        self.source_code_path = source_code_path + '\n--------------------\n' + str(len(self.path.split())-1) + '\n--------------------\n' + self.file_name + '\n' + str(self.factors) + '\n' + str(self.path_finder.flog_scores[self.index]) + '\n' + source_code
            
    def successors(self):
        successors = []
        my_flog = self.path_finder.flog_scores[self.index]
        for i in range(self.path_finder.dist_matrix.shape[0]):
            suc = ChainLink(self.path_finder, i, self.index, self.path, self.source_code_path, self.factor_path)
            other_flog = self.path_finder.flog_scores[suc.index]
            flog_jump = my_flog - other_flog
            ast_jump = self.path_finder.dist_matrix[self.index, suc.index]
            if other_flog < my_flog and flog_jump < Chain.FLOG_JUMP_UPPERBOUND and flog_jump > Chain.FLOG_JUMP_LOWERBOUND and ast_jump < Chain.AST_JUMP_UPPERBOUND:
                successors.append(suc)
        return successors
    
    def closest_successor_with_lower_value(self, feature_list):
        next_index = closest_submission_with_lower_values(self.index, self.path_finder.dist_matrix, feature_list)
        if next_index < 0:
            return None
        else:
            return ChainLink(self.path_finder, next_index, self.index, self.path, self.source_code_path, self.factor_path)
    
    def __cmp__(self, other):
        return cmp(self.value, other.value)

def main():
    parser = argparse.ArgumentParser(description='Find paths from submissions with high flog scores to submissions with low ones.')
    parser.add_argument('start_index', type=int, help='Index of the start submission in the index file')
    parser.add_argument('-m', '--manual', action='store_true', help='Include this option to generate a path based on manually defined features')
    parser.add_argument('-p', '--plot_factors', action='store_true', help='Include this option to plot the factors using parallel coordinates after generating the path')
    args = parser.parse_args()
    start_index = args.start_index
    manual = args.manual
    plot_factors = args.plot_factors
    pf = Chain('data/src')
    initial_state = ChainLink(pf, start_index, 0, '', '', None)
    if manual:
        man_features = np.loadtxt('manual_features.np')
        flog_features = np.loadtxt('data/feature/flog_feature.np')
        submissions, source_code, factors = pf.closest_improvement_sequence(initial_state, (man_features, flog_features))
        print source_code
        if plot_factors:
            pplot = parallel_coordinates(factors)
            pplot.show()
    else:
        submissions, source_code, factors = pf.find_path(initial_state)
        print source_code
        if plot_factors:
            pplot = parallel_coordinates(factors)
            pplot.show()
    

if __name__ == "__main__":
    main()