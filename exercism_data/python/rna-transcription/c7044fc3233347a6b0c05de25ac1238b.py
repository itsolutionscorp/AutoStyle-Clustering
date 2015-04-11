class DNA(object):
    
    def __init__(self, DNASequence):
      self.sequence = DNASequence
      
    # Method: to_rna
    #  Arguments:  self      - 
    #              sequence - String sequence that is to be converted to an RNA string (replace "T" with "U").
    #  Returns: RNA String
      
    def to_rna(self):
	  return self.sequence.replace("T", "U")
