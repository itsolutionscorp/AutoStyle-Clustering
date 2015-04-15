require 'byebug'

class Complement
  def self.of_dna(dna_strand)
    return dna_strand.gsub(/[G,C,T,A]/, 'G' => 'C', 'C' => 'G',  'T' => 'A',  'A' => 'U' )
  end

  def self.of_rna(rna_strand)
    return rna_strand.gsub(/[G,C,T,A,U]/, 'G' => 'C', 'C' => 'G',  'T' => 'A', 'A' => 'T', 'U' => 'A' )
  end
end
