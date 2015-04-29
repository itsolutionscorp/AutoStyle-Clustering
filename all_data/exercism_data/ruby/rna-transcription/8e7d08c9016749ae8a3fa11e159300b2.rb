class Complement
  DNA_STRAND_COMPLEMENTS = { 'A' => 'U', 'T' => 'A', 'G' => 'C', 'C' => 'G' }
  
  def self.of_dna dna_string
    dna = Strand.new dna_strand
    dna.map_complements DNA_STRAND_COMPLEMENTS
  end

  def self.of_rna rna_string
    rna = Strand.new rna_string
    rna.map_complements DNA_STRAND_COMPLEMENTS.invert
  end

end

class Strand
  attr_accessor :nucleotides

  def initialize nucleotides
    self.nucleotides = nucleotides.chars
  end

  def map_complements complement_array
    nucleotides.map do |nucleotide|
      raise ArgumentError unless complement_array[nucleotide]
      complement_array[nucleotide]
    end.join
  end 

end
