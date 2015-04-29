module RNATranscriber 
  
  def self.included(base)
    base.extend(ClassMethods)
  end
  
  module ClassMethods
 
    def complement_dna(nucleotides)
      complement = {"G"=>"C", "C"=>"G", "A"=>"U", "T"=>"A"}
      nucleotides.each_char.inject("") { |rna, nucleotide| rna + complement[nucleotide] }
    end
 
    def complement_rna(nucleotides)
      complement = {"G"=>"C", "C"=>"G", "U"=>"A", "A"=>"T"}
      nucleotides.each_char.inject("") { |dna, nucleotide| dna + complement[nucleotide] }
    end

  end

end

class Complement

  include RNATranscriber
  
  def self.of_dna(nucleotides)
    Complement.complement_dna(nucleotides)
  end

  def self.of_rna(nucleotides)
    Complement.complement_rna(nucleotides)
  end

end
