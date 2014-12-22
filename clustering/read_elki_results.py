#!/usr/bin/python

import glob
import re
import numpy as np
import sys
import argparse

def read_elki(cluster_dir, n):
    clusters = -1*np.ones(n)

    res = [f for f in glob.glob(cluster_dir + '/*') if re.search('cluster_\d+_\d+.txt', f)]
    i = 0
    for f in res:
        with open(f, 'r') as cluser_file:
            for line in cluser_file:
                match = re.match('ID=\d+', line)
                if match:
                    clusters[int(match.group(0)[3:])-1] = i
        i+=1
    return clusters


def main():
    parser = argparse.ArgumentParser(description='Read the output from Elki into a numpy array.')
    parser.add_argument('cluster_dir', help='directory that contain the output from Elki')
    parser.add_argument('num_points', help='number of points in the data set')
    args = parser.parse_args()
    cluster_dir = args.cluster_dir
    n = args.num_points

    read_elki(cluster_dir, int(n))

if __name__ == '__main__':
    main()