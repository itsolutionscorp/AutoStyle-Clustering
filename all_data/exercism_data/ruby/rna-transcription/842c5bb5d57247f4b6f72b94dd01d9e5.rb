class Complement
  PAIRS = [['A', 'T'], ['G', 'C']]
  DNA_COMPLEMENTS = Hash[PAIRS].merge(Hash[PAIRS.map(&:reverse)])
  RNA_SUBSTITUTE  = ['T', 'U']
  
  def self.of_dna dna
    substitute_thymine dna_pair_of(dna)
  end
  
  def self.of_rna rna
    substitute_uracil dna_pair_of(substitute_uracil rna)
  end
  
  def self.dna_pair_of dna
    dna.each_char.map {|char| DNA_COMPLEMENTS[char]}.join
  end
  
  def self.substitute_thymine rna
    rna.gsub(*RNA_SUBSTITUTE)
  end
  
  def self.substitute_uracil rna
    rna.gsub(*RNA_SUBSTITUTE.reverse)
  end
  
end
