# How to integrate new batch data

1. Filter out stuff that isn't correct using an autograder

2. Clean code down to just the function you want, and number source files from 0 to n

3. Build the ASTs, and a tree edit distance matrix

4. Copy in the appropriate all_libcalls.txt, style_scores_names.np, and style_features_names.np. As of now, there is one per language.

5. 
    python batch_features.py [function name] [language] [data_dir]/feature/style_scores.np [data_dir] [flog or abc]

6. 
    python batch_features.py [function name] [language] [data_dir]/feature/style_features.np [data_dir] [list of all features you want]

7. Use featurization/libcalls_and_linenums.rb to generate a mapping of library calls to line numbers. 

7. Run the web ui!

Note: Right now, in order for style_chain.py to work, you must use exactly 8 structural features, which must be the last features. This means at the end of your features list you must include control_flow, recursion, and duplicate_treegram

Ultimately, the required directory structure is:

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
            libcalls_and_linenums.json