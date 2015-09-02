'''
Created on Apr 16, 2015

@author: jmoghadam
'''

import argparse
import os
import subprocess

NUM_STUDENTS = 950

def terminal_command(*args):
    '''
    Calls the terminal command given by args, and
    returns its output as a string.
    '''
    subprocess.Popen(args, stdout=subprocess.PIPE)

def main():
    parser = argparse.ArgumentParser(description='Download student submissions from 61B repo')
    parser.add_argument('assignment', help='Name of assignment e.g. hw1, lab3, proj2.')
    parser.add_argument('filename', help='Name of file to download.')
    parser.add_argument('token', help='Token to authorize downloads.')

    args = parser.parse_args()
    assignment = args.assignment
    filename = args.filename
    token = args.token
    letters = 'abcdefghijklmnopqrstuvwxyz'
    i = 0
    for l1 in letters:
        for l2 in letters:
            for l3 in letters:
                directory = "61b_data/src/" + assignment + "/" + str(i) + "/"
                if not os.path.exists(directory):
                    os.makedirs(directory)
                command = "curl -A 'Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0' -H 'Authorization: token " + token + "' -H 'Accept: application/vnd.github.v3.raw' -o " + directory + filename + " -L https://raw.githubusercontent.com/Berkeley-CS61B/" + l1 + l2 + l3 + "/submit/" + assignment + "/" + assignment + "/" + filename
                print command
                os.system(command)
                i += 1
                if i > NUM_STUDENTS:
                    return
                
if __name__ == '__main__':
    main()
