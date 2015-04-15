#!/usr/bin/env python

import os
import numpy as np
import argparse
from sklearn.decomposition import ProjectedGradientNMF
from collections import defaultdict

def sort_by_row(matrix):
    sorted_indexes = np.argsort(matrix, axis = 1)
    output = []
    for i in range(sorted_indexes.shape[0]):
        output.append(np.array([sorted_indexes[i][::-1], np.sort(matrix[i])[::-1]]).T)
    return output

def savetxt_3d(data_3d, out_path, delimiter_word):
    try:
        os.remove(out_path)
    except OSError:
        pass
    with file(out_path, 'w') as outfile: 
        outfile.write('# Array shape: {0}\n'.format(np.array(data_3d).shape))
        for i, data_slice in enumerate(data_3d):
            if delimiter_word == "submission":
                outfile.write('# {0} {1} \n'.format(delimiter_word, int(indexes[i])))
            else:
                outfile.write('# {0} {1} \n'.format(delimiter_word, i + 1))
            np.savetxt(outfile, data_slice, fmt='%.4f')
            outfile.write('\n')

def show_feature_name(data_file, feature_list):
    with open(data_file) as f:
        lines = map(str.rstrip, f.readlines())
    f.close()

    f = open(data_file, "w+")
    new_lines = []
    for line in lines:
        if line and line[0] != '#':
            #new_lines.append(line + " " + feature_list[int(float(line.split()[0]))])
            new_lines.append(line.split()[1] + "    " + feature_list[int(float(line.split()[0]))])
        else:
            new_lines.append(line) 
    f.write('\n'.join(new_lines))
    f.close()        


if __name__ == '__main__':
    global indexes

    parser = argparse.ArgumentParser(description='Compute Non-negative Matrix Factorization')
    parser.add_argument('data_matrix', help='path to data file, should be readable by numpy')
    parser.add_argument('k', type=int, help='number of components to keep')
    parser.add_argument('feature_list', help='path to file containing list of feature names')
    parser.add_argument('index_file', help='path to array_index for this dataset')
    
    args = vars(parser.parse_args())
    data = np.loadtxt(args['data_matrix'])
    k = args['k']
    with open(args['feature_list']) as f:
        feature_list = map(str.rstrip, f.readlines())
    indexes = np.loadtxt(args['index_file'])

    model = ProjectedGradientNMF(n_components=k, init='random', random_state=0)
    H = model.fit_transform(data) # H is submissions(row) by factors(cols)
    W = model.components_    # W is factors(row) by features(cols)
    magnitude = np.prod([np.sum(H, axis = 0), np.sum(W, axis = 1)], axis = 0)

    savetxt_3d(np.array(sort_by_row(W))[:, 0:20, :], 'nmf/factors_and_sorted_features.np', "factor")
    show_feature_name('nmf/factors_and_sorted_features.np', feature_list)

    subs_and_sorted_factors = sort_by_row(H)
    for sub in subs_and_sorted_factors:
        for factor in sub:
            factor[0] += 1
    savetxt_3d(subs_and_sorted_factors, 'nmf/subs_and_sorted_factors.np', "submission")

    print "\n-------------- pattern of dominating factors ----------------\n"

    pattern = []
    for i, sub in enumerate(subs_and_sorted_factors):
        sub_pattern = []
        sub_pattern.append(sub[0][0])
        j = 0
        while sub[j+1][1] > 0.01 and sub[j+1][1] / sub[j][1] >= 0.25: 
            sub_pattern.append(sub[j+1][0]) 
            j += 1
        pattern.append(sub_pattern)
    
    s = []
    for i, df in enumerate(pattern):
        s.append((i, df))
    res = defaultdict(list)
    for i, df in s:
        res[str(df)].append(indexes[i])

    for k, v in res.iteritems():
        print str(k) + " --- " + str(len(v)) + " --- " + str(v) + "\n"

    count = 0
    total = 0
    for k, v in res.iteritems():
        total += len(v)
        if len(v) >= 10:
            count += len(v)
    print "\n--------------- {0} -----------------\n".format(count / float(total))



    print "\n-------------- dominating factor ------------------\n"

    most_correlated_factors_for_subs = np.array(subs_and_sorted_factors)[:, 0:1, 0:1].flatten()
    # subs_and_sorted_factors = subs x factors x [ index of factor, factor weight ]
    s = []
    for i, f in enumerate(most_correlated_factors_for_subs):
        s.append((i, f)) 
    res = defaultdict(list)
    for i, f in s:
        res[f].append(indexes[i])

    for k, v in res.iteritems():
        print str(int(k)) + " --- " + str(len(v)) + " --- " + str(v) + "\n"


    np.savetxt("nmf/factors_magnitude.np", magnitude, fmt='%.2f')
    np.savetxt("nmf/transformed_data.np", H, fmt='%.5f')
    np.savetxt("nmf/non_nega_components.np", W, fmt='%.5f')
