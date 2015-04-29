
class DNA (object):
	'''translator = { \
		'G': 'C', \
		'C': 'G', \
		'T': 'A', \
		'A': 'U' \
		}'''
	from string import maketrans 
	intab, outtab = 'GCTA', 'CGAU'
	translator = maketrans(intab, outtab)
		
	def __init__(self, sequence=None): 
		self.strand = sequence     # uninitialized DNA strand
			
	def to_rna(self):
		rna_equiv = None  # initialize the RNA equivalent
		if self.strand:  # proceed only if DNA seq is available		
			#rna_equiv = "".join(DNA.translator[n] for n in self.strand)
			rna_equiv = strand.translate(translator)
		return rna_equiv

'''
Defining Classes http://buff.ly/1kMx6Mj

# Rna Transcription

Write a program that, given a DNA strand, returns its RNA complement (per RNA transcription).

Both DNA and RNA strands are a sequence of nucleotides.

The four nucleotides found in DNA are adenine (**A**), cytosine (**C**), guanine (**G**) and thymidine (**T**).

The four nucleotides found in RNA are adenine (**A**), cytosine (**C**), guanine (**G**) and uracil (**U**).

Given a DNA strand, its transcribed RNA strand is formed by replacing each nucleotide with its complement:

* `G` -> `C`
* `C` -> `G`
* `T` -> `A`
* `A` -> `U` 
'''

'''
class Dog:
    def __init__(self, legs, colour):
        self.legs = legs
        self.colour = colour

fido = Dog(4, "brown")
spot = Dog(3, "mostly yellow")
'''

'''
def test_transcribes_cytidine_unchanged(self):
        self.assertEqual('C', dna.DNA('G').to_rna())
def test_transcribes_all_occurences(self):
        self.assertEqual(
            'UGCACCAGAAUU',
            dna.DNA('ACGTGGTCTTAA').to_rna()
        )        
        
class DNA(object):

    def __init__(self, code):
        self._code = code

    def to_rna(self):
        translate = dict(G='C', C='G', T='A', A='U')
        return ''.join(translate[char] for char in self._code)
'''
