# How to integrate new data

1. Filter out stuff that isn't correct using an autograder

2. Build a distance matrix

3. Copy in the appropriate all_libcalls.txt, style_scores_names.np, and style_features_names.np. As of now, there is one per language.

4. 

    python batch_features.py [function name] [language] [data_dir]/feature/style_scores.np [data_dir] [flog or abc]

5. 

    python batch_features.py [function name] [language] [data_dir]/feature/style_features.np [data_dir] [list of all features you want]

6. Run the web ui!

Note: Right now, in order for style_chain.py to work, you must use exactly 8 structural features, which must be the last features. This means at the end of your features list you must include control_flow, recursion, and duplicate_treegram

Ultimately, the required directory structure is:

    [data_dir]/
        src/
            [all of your source code]
        gen/
            ast_dist_matrix.np
        feature/
            all_libcalls.txt
            style_scores.np
            style_scores_names.np
            style_features.np
            style_features_names.np
        ast/
            [all of your ast files, for ruby]