class Complement
  PAIRS = [['A', 'T'], ['G', 'C']]
  DNA_COMPLEMENTS = Hash[PAIRS].merge(Hash[PAIRS.map(&:reverse)])
  
  def self.of_dna dna
    dna_pair_of(dna).gsub('T', 'U')
  end
  
  def self.of_rna rna
    dna_pair_of(rna.gsub('U', 'T')).gsub('U', 'T')
  end
  
  def self.dna_pair_of dna
    dna.each_char.map {|char| DNA_COMPLEMENTS[char]}.join
  end
  
end
