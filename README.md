# Synopsis

This repository contains all code and data associated with the Autostyle research project. Right now, the primary thing Autostyle does is: You feed it the source code of a lot of different source code attempting to solve the same programming assignment, and then you can ask Autostyle to provide hints and visualization for how to improve the style of any one of those submissions.

Autostyle makes an important assumption that all the submissions you give it solve the problem correctly.

Autostyle is made up of a number of scripts, but the three main components are: 

1. The web_ui, which you can start by running `run.py` in the `web_ui` folder.

2. `style_chain.py`, which is the backend of the web ui. It's in the `visualization` folder. This handles all the logic for how to gneerate a hint for a submission.

3. `individual_features.py`, which can generate features for each submission. Calculating features for a submission allow you to turn that submission into a vector, allowing you to easily manipulate and compare your data.

# How to integrate new batch data

1. Create a directory that you will store the processed data in. It should probably be in `assignments/[language of your choice]/[assignment name]`. This directory is henceforth called `[data_dir]`.

2. Filter our submission that aren't correct using some autograder, number your source files 0 to n, and clean your code down to just the function you want. Then store this stuff in `[data_dir]/src`.

3. Create a matrix of the tree edit distance between every pair of submissions. Store this in `[data_dir]/gen/ast_dist_matrix.np`.

4. In the folder `[data_dir]/feature/`, add in the appropriate `all_libcalls.txt`, `style_scores_names.np`, and `style_features_names.np`. As of now, there is one per language, so copy them over from an existing directory if you can, or else you'll have to make them. `all_libcalls.txt` is just a text file containing the name of everything you want to be considered as a library function call. `style_scores_names.np` and `style_features_names.np` are files containing the names of the different features that you used. For example, one of these files might have the name `recursion`, which means you included a feature that indicates whether or not the function uses recursion.

5. Generate the style scores for all of your code:
    python batch_features.py [function name] [language] [data_dir] [data_dir]/feature/style_scores.np [flog or abc]

6. Generate the features for all of your code:
    python batch_features.py [function name] [language] [data_dir] [data_dir]/feature/style_features.np -l [data_dir]/feature/feature_line_nums.np [list of all features you want, choices include libcall, control_flow, recursion, duplicate_treegram]

    Note: Right now, in order for style_chain.py to work, you must use exactly 8 structural features, which must be the last features. This means at the end of your features list you must include control_flow, recursion, and duplicate_treegram

    After all of these steps, the directory structure should be:

    [data_dir]/
        src/
            [all of your clean source code]
        gen/
            ast_dist_matrix.np
        feature/
            all_libcalls.txt
            style_scores.np
            style_scores_names.np
            style_features.np
            style_features_names.np
            feature_line_nums.np

7. Run the web ui!

# How to run the web_ui:

1. `cd web_ui/`
2. `python run.py`
3. navigate to localhost:5000 in your browser

# Tour of the repo

- `all_data` contains bulk, unprocessed data that we downloaded

- `archive` contains old scripts we're no longer using

- `assignments` contains data that has been processed into a form that is usable by the web ui. For example, `assignments/ruby/hamming` is an example of something that could be a data_dir mentioned above.

- `autograder` contains code for integrating with an autograding system. Ask Mukund about this.

- `clustering` contains scripts for clustering data. I honestly don't remember what a lot of the stuff in here does, since it's been a long time since the project was focused around clustering. However, if you wanted to do clustering again, the main script you would probably want to look at is `cluster_algorithms.py`. It can take in data in the form of a matrix (each row is a feature vector for one piece of data), and then ouput a vector where each number in the vector indicates that the corresponding data point belongs to a cluster with that number.

- `featurization` contains all code related to creating feature vectors for a piece of code. All the main action is in `individual_features.py`. The other files are mostly leftover from before things were integrated into individual_features, and are probably outdated. Individual features generates the feature vector for a single submission. You can use `batch_features.py` to generate features for a bunch of data at once, though all this does is call individual_features in a loop.

- `scripts_java` contains special scripts for building ASTs from java code. The file you want to use here is `ASTWriter.java`, which creates all the asts for all the java files in a particular data directory. `ASTBuilder.java` will do this for a single submission. `ASTWriter.java` will also created cleaned source code for you, so that the web ui can just display the lone method and not the whole class.

- `scripts_python` contains some scripts in python. I'm actually not sure what these are for -- you'll have to ask one of the other people that worked on this project.

- `syntax_tree` contains some code for manipulating abstract syntax trees. There are a few different scripts for creating abstract syntax trees for ruby. `ast_with_lines.rb` is the simplest and most modern one -- the others are outdated. `tree.py` used to be useful too, but I think by now it has been completely oudated (luckily, since the file is a mess). It has one useful function though, which is `printTree`, that allows you to print out visualize trees, where the trees are in python `Node` objects.

- `util.py` contains some miscellaneous utility scripts. I don't actually think any of them are in regular use.

- `visualization` contains scripts for creating various visualizations of the data. This is essentially what the project is all about. This is where you'll find `style_chain.py`, which is the backend of the web ui (It can also be run from the command line for some simple output). The other scripts in here are related to clustering, which is outdated. Apparently Hezheng has a newer and fancier clustering visualizer, so if you do want to visualize the clusters you should ask him about it.

- `web_ui` contains code for running the web ui. Ask Rohan about this.

# Miscellaneous

You may notice references to files with the extension `.np`. This just means it's like a matrix or vector that can be read by numpy.

Most scripts should be run from the top level of the repo. The exception is the script to run the web ui.

# Contributors

- Joseph Moghadam, moghadam.joseph@gmail.com. If you have any questions about what anythin in the repo does, please ask me! I should be pretty responsive.