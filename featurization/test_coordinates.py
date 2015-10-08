import argparse
import numpy as np
from calc_tsne import calc_tsne


def generate_coordinates(matrix_file, clusters_file, flog_file):
    '''
    Perform t-SNE on a distance matrix and write 2D coordinates to a csv file
    matrix_file: file that contains features of distance matrix
    '''
    matrix = np.loadtxt(matrix_file)
    clusters = np.loadtxt(clusters_file).astype(int)
    flog_feature = np.loadtxt(flog_file)

    plotting_data = calc_tsne(data, PERPLEX=30)
    clusters.shape = (matrix.shape[0], 1)
    flog_feature.shape = (matrix.shape[0], 1)
    filename = np.array(range(1, matrix.shape[0] + 1))
    filename.shape = (matrix.shape[0], 1)
    csv_output_data = np.concatenate((plotting_data, lusters), axis=1)
    csv_output_data = np.concatenate((csv_output_data, flog_feature), axis=1)
    csv_output_data = np.concatenate((csv_output_data, filename), axis=1)
    np.savetxt("coordinates.csv", csv_output_data, fmt='%.2f', delimiter=',', header='xaxis,yaxis,cluster,flog,filename')

def main():
   
    parser = argparse.ArgumentParser(description='Cluster submissions and generate coordinates.csv')
    parser.add_argument('assignment_directory')
    assignment_directory = args.assignment_directory

    matrix_file = os.join(assignment_directory, "/gen/ast_dist_matrix.np")
    cluster_file = os.join(assignment_directory, "/gen/clusters.np")
    flog_file = os.join(assignment_directory, "/feature/style_scores.np")
    spectral_file = os.join(assignment_directory, "/gen/spectral_features.np")

    os.system("spectral_clustering.py " + matrix_file + " 6 -o " + spectral_file) 
    os.system("cluster_algorithm.py optics " + spectral_file + " -o " + cluster_file + " --min-sample 40 --xi 0.035")
    generate_coordinates(matrix_file, cluster_file, flog_file);
