module RNATranscriber 
  
  def self.included(base)
    base.extend(ClassMethods)
  end
  
  module ClassMethods
 
    def complement_dna(nucleotides)
      complement = {"G"=>"C", "C"=>"G", "A"=>"U", "T"=>"A"}
      nucleotides.chars.map { |nucleotide| complement[nucleotide] }.join("")
    end
 
    def complement_rna(nucleotides)
      complement = {"G"=>"C", "C"=>"G", "U"=>"A", "A"=>"T"}
      nucleotides.chars.map { |nucleotide| complement[nucleotide] }.join("")
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
