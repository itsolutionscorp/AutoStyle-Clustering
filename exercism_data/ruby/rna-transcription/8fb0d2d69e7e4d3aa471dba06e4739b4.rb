class Complement
  DNA_STRAND_COMPLEMENTS = { 'A' => 'U', 'T' => 'A', 'G' => 'C', 'C' => 'G' }
  
  def self.of_dna dna_strand
    dna = Strand.new dna_strand.chars
    dna.map_complements DNA_STRAND_COMPLEMENTS
  end

  def self.of_rna rna_string
    rna = Strand.new rna_string.chars
    rna.map_complements DNA_STRAND_COMPLEMENTS.invert
  end

end

class Strand
  attr_accessor :nucleotides

  def initialize nucleotides
    self.nucleotides = nucleotides
  end

  def map_complements complement_array
    nucleotides.map do |nucleotide|
      complement_array.fetch(nucleotide) do |arg| 
        raise ArgumentError, "#{arg} is not a valid nucleotide"
      end
    end.join
  end 

end
