
#fairly straight-forward, and due to python's inherent readability,
#probably not needing many comments, but I like writing them!

#GCTA
#CGAU
#still not getting why everyone's so classist on exercism. purported modularity, I presume?

def to_rna(DNAstrand):
    return DNAstrand.translate(str.maketrans("GCTA","CGAU")) #returns a mapped DNAstrand, respecting the RNA translation

#excusing excessive commentary, more or less readable?
        #Personally I like the dictionary method better for its readability
                #and its ease of access for edits
