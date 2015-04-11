##                  #
# Complement ########
##                  #

class Complement
  
  attr_reader :strand_dna 
  attr_reader :strand_rna
  
  def self.of_dna(strand_dna)
    strand_dna.upcase.gsub(/[CGTA]/, 'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U')
  end

  def self.of_rna(strand_rna)
    strand_rna.upcase.gsub(/[CGAU]/, 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A')
  end

end
