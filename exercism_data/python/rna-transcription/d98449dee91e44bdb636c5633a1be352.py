import re

class DNA :
   def __init__(self, dna) :
      self.dna = dna

   def to_rna(self) :
      return re.sub(r'T', r'U', self.dna) or self.dna
      
