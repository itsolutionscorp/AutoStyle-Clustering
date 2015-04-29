#!/usr/bin/env python

import os
import sys
import pdb
import numpy as np
import argparse

'''
Some Good Examples
17987.rb belongs to complicated cluster, flog score 20 bar, support -u
19450.rb is a canonical solution, support -u 
16266.rb is a complicated solution, flog score 78, support -d
'''

def find_smallest_above_threshold(array, t):
  sorted_array = np.sort(array)
  value = sorted_array[np.where(sorted_array>t)[0][0]]
  ind = np.where(array==value)[0][0]
  return value, ind

def find_nth_above_threshold(array, t, n):
  sorted_array = np.sort(array)
  sort_indexes = np.argsort(array)
  ind_in_sorted_array = np.where(sorted_array>t)[0][n]
  ind = sort_indexes[ind_in_sorted_array]
  value = sorted_array[ind_in_sorted_array]
  return value, ind
  
def get_index_by_name(n):
  return np.where(indexes==n)[0][0]

def dist_by_name(n1, n2):
  ind1 = get_index_by_name(n1) 
  ind2 = get_index_by_name(n2) 
  return dist_matrix[ind1][ind2]

def closest_by_name(n1):
  ind1 = get_index_by_name(n1) 
  min_dist = np.sort(dist_matrix[ind1])[1] 
  min_ind = np.where(dist_matrix[ind1]==min_dist)[0][0]
  return min_dist, min_ind 

def closest_by_name_with_min_threshold(n1, t):
  ind1 = get_index_by_name(n1) 
  return find_smallest_above_threshold(dist_matrix[ind1], t)

def generate_chain_with_min_threshold(start, clen, t):
  start_ind = get_index_by_name(start)
  chain_names = []; chain_indexes = []; distances = [] 
  chain_names.append(start)
  chain_indexes.append(start_ind)
  current_ind = start_ind
  for i in range(clen - 1):
    dist, next_ind = find_smallest_above_threshold(dist_matrix[current_ind], t)
    if i > 0 and next_ind == chain_indexes[-2]:
      dist, next_ind = find_nth_above_threshold(dist_matrix[current_ind], t, 1)
    current_ind = next_ind
    chain_names.append(indexes[current_ind])
    chain_indexes.append(current_ind)
    distances.append(dist)
  return chain_names, chain_indexes, distances

def new_generate_chain_with_min_threshold(start, clen, t):
  start_ind = get_index_by_name(start)
  chain_names = []; chain_indexes = []; distances = []; flog_scores = []
  chain_names.append(int(start))
  chain_indexes.append(start_ind)
  flog_scores.append(int(flogs[start_ind]))
  current_ind = start_ind
  for i in range(clen - 1):
    dist, next_ind = find_smallest_above_threshold(dist_matrix[current_ind], t)
    n = 1
    while next_ind in chain_indexes:
      dist, next_ind = find_nth_above_threshold(dist_matrix[current_ind], t, n) 
      n += 1
    current_ind = next_ind
    chain_names.append(int(indexes[current_ind]))
    chain_indexes.append(current_ind)
    distances.append(int(dist))
    flog_scores.append(int(flogs[current_ind]))
  return chain_names, chain_indexes, distances, flog_scores

def generate_iflog_chain_with_min_threshold(start, clen, t):
  start_ind = get_index_by_name(start)
  chain_names = []; chain_indexes = []; distances = []; flog_scores = []
  chain_names.append(int(start))
  chain_indexes.append(start_ind)
  flog_scores.append(int(flogs[start_ind]))
  current_ind = start_ind
  for i in range(clen - 1):
    dist, next_ind = find_smallest_above_threshold(dist_matrix[current_ind], t)
    n = 1
    while (next_ind in chain_indexes) or (flogs[next_ind] <= flogs[current_ind]):
      dist, next_ind = find_nth_above_threshold(dist_matrix[current_ind], t, n)
      n += 1
    current_ind = next_ind
    chain_names.append(int(indexes[current_ind]))
    chain_indexes.append(current_ind)
    distances.append(int(dist))
    flog_scores.append(int(flogs[current_ind]))
  return chain_names, chain_indexes, distances, flog_scores

def generate_dflog_chain_with_min_threshold(start, clen, t):
  start_ind = get_index_by_name(start)
  chain_names = []; chain_indexes = []; distances = []; flog_scores = []
  chain_names.append(int(start))
  chain_indexes.append(start_ind)
  flog_scores.append(int(flogs[start_ind]))
  current_ind = start_ind
  for i in range(clen - 1):
    dist, next_ind = find_smallest_above_threshold(dist_matrix[current_ind], t)
    n = 1
    while (next_ind in chain_indexes) or (flogs[next_ind] >= flogs[current_ind]):
      dist, next_ind = find_nth_above_threshold(dist_matrix[current_ind], t, n)
      n += 1
    current_ind = next_ind
    chain_names.append(int(indexes[current_ind]))
    chain_indexes.append(current_ind)
    distances.append(int(dist))
    flog_scores.append(int(flogs[current_ind]))
  return chain_names, chain_indexes, distances, flog_scores



if __name__ == '__main__':
  global dist_matrix, indexes, flogs

  parser = argparse.ArgumentParser(description='Some small functions help to explore data')
  parser.add_argument('n1', type=float)
#parser.add_argument('n2', type=float)
  parser.add_argument('-m', '--matrix_file', default="/Users/Beaver/CS169_Clustering/800/ast686/dist_matrix_686", help='path to distance matrix file, should be readable by numpy')
  parser.add_argument('-i', '--index_file', default="/Users/Beaver/CS169_Clustering/800/ast686/array_index", help='path to array_index for this dataset')
  parser.add_argument('-f', '--flog_file', default="/Users/Beaver/CS169_Clustering/800/feature686/flog_feature.np", help='path to flog feature file')
  parser.add_argument('-u', '--up', action='store_true')
  parser.add_argument('-d', '--down', action='store_true')
  
  args = vars(parser.parse_args())
  dist_matrix = np.loadtxt(args['matrix_file'])
  indexes = np.loadtxt(args['index_file'])
  flogs = np.loadtxt(args['flog_file'])
  
#print dist_by_name(args['n1'], args['n2'])
#print closest_by_name(args['n1'])
#print closest_by_name_with_min_threshold(args['n1'], 30.0)
#print generate_chain_with_min_threshold(args['n1'], 15, 10.0)
  cn, ci, d, f = new_generate_chain_with_min_threshold(args['n1'], 15, 10.0)
  print "\n"
  print "chain names: " + str(cn) + "\n"
  print "distances: " + str(d) + "\n"
  print "flog scores: " + str(f) + "\n"

  if args['up']:
    print "\n---------------------------\n"
    cn, ci, d, f = generate_iflog_chain_with_min_threshold(args['n1'], 15, 10.0)
    print "chain names: " + str(cn) + "\n"
    print "distances: " + str(d) + "\n"
    print "flog scores: " + str(f) + "\n" 

  if args['down']:
    print "\n---------------------------\n"
    cn, ci, d, f = generate_dflog_chain_with_min_threshold(args['n1'], 10, 10.0)
    print "chain names: " + str(cn) + "\n"
    print "distances: " + str(d) + "\n"
    print "flog scores: " + str(f) + "\n"
