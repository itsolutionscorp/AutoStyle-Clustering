#!/usr/bin/env python
'''
Created on Sep 13, 2014

@author: joe
'''

import argparse
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.cm as cm

class HistogramPlotter:
    def __init__(self, data, source_dir, index, bins=10):
        self.data = data
        self.source_dir = source_dir
        self.index = index
        self.bins = bins
        
    def plot(self):
        fig = plt.figure()
        ax = fig.add_subplot(1,1,1)
        ax.set_title('Histogram')
        
        cluster_data = [self.data,]
        n, bins, hist_patches = ax.hist(cluster_data, self.bins, picker=True)

        self.patch_to_range = {}
        for i in xrange(len(hist_patches)):
            patch = hist_patches[i]
            self.patch_to_range[patch] = (bins[i], bins[i+1])
            
        def onpick(event):
            patch = event.artist
            all_source_code = self.get_source_in_range(self.patch_to_range[patch])
            print ("####################\n")
            for code in all_source_code:
                print code
                print ("--------------------\n")
            
        fig.canvas.mpl_connect('pick_event', onpick)
        
    def get_source_in_range(self, bounds):
        source_code = []
        print bounds
        filtered_index = self.index[np.logical_and(self.data >= bounds[0], self.data <= bounds[1])]
        for index in filtered_index:
            file_path = self.source_dir + '/' + str(index) + '.rb'
            with open(file_path.strip(), 'r') as f:
                source_code.append(file_path + '\n' + f.read())
        return source_code
        
    def show(self):
        plt.show()


class HistogramClusterPlotter:

    def __init__(self, data, source_dir, index, bins, clusters=None):
        self.data = data
        self.source_dir = source_dir
        self.index = index
        self.bins = bins
        self.clusters = clusters

        self.k = np.unique(clusters).shape[0] if clusters is not None else 1
        self.colors = cm.rainbow(np.linspace(0,1,self.k))

    def plot(self):
        
        fig = plt.figure()
        ax = fig.add_subplot(1,1,1)
        ax.set_title('Histogram')
        
        if self.clusters is None:
            cluster_data = [self.data,]
            ax.hist(cluster_data, self.bins)
            return
        cluster_data = []
        labels = []
        for cluster_num in np.unique(self.clusters):
            filtered_data = self.data[self.clusters==cluster_num]
            cluster_data.append(filtered_data)
            labels.append(str(filtered_data.shape[0]) + " point cluster" )

        n, bins, hist_patches = ax.hist(cluster_data, self.bins, label=labels, picker=True)

        self.patch_to_range = {}
        for bar_container in hist_patches:
            for i in xrange(len(bar_container)):
                patch = bar_container[i]
                self.patch_to_range[patch] = (bins[i], bins[i+1])
            
        def onpick(event):
            patch = event.artist
            all_source_code = self.get_source_in_range(self.patch_to_range[patch])
            print ("####################\n")
            for code in all_source_code:
                print code
                print ("--------------------\n")
            
        fig.canvas.mpl_connect('pick_event', onpick)
        
    def get_cluster_source(self, i):
        source_code = []
        filtered_index = self.index[self.clusters==i]
        for index in filtered_index:
            file_path = self.source_dir + '/' + str(index) + '.rb'
            with open(file_path.strip(), 'r') as f:
                source_code.append(file_path  + '\n' + f.read())
        return source_code

    def get_source_code(self, i, offset):
        print i
        print offset
        submission_file = str(self.index[i+offset].astype(int))
        print submission_file
        file_path = self.source_dir + '/' + submission_file + '.rb'
        with open(file_path.strip(), 'r') as submission_file:
            return submission_file.read()

    def onpick(self, event, offset):
        print ("####################\n")
        ind = event.ind
        for i in ind:
            print (self.get_source_code(i, offset))
            print ("--------------------\n")

    def show(self):
        plt.show()

def main():
    
    parser = argparse.ArgumentParser(description='Plot histograms.')
    parser.add_argument('data', help='location of file that contains the score of each point')
    parser.add_argument('source', help='location of folder that contains the source code of points to be plotted')
    parser.add_argument('index', help='location of file that maps index in the feature/cluster file to name of source code. As of now, file names here should not include the .rb at the end')
    parser.add_argument('-c', '--clusters', help='location of file that contains a list of clusters.')
    parser.add_argument('-b', '--bins', type=int, default=10, help='number of bins in the histogram, default 10')

    args = parser.parse_args()
    data = np.loadtxt(args.data)
    source_dir = args.source
    index = np.loadtxt(args.index).astype(int)
    bins = args.bins
    cluster_file = args.clusters
    if cluster_file:
        c = HistogramClusterPlotter(data, source_dir, index, bins, np.loadtxt(cluster_file))
    else:
        c = HistogramPlotter(data, source_dir, index, bins)
    c.plot()
    c.show()

if __name__=='__main__':
    main()