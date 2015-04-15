module RNATranscriber 
  
  def self.included(base)
    base.extend(ClassMethods)
  end
  
  module ClassMethods
    DNA_COMPLEMENT = {"G"=>"C", "C"=>"G", "A"=>"U", "T"=>"A"}
    RNA_COMPLEMENT = { "C"=>"G", "G"=>"C", "U"=>"A", "A"=>"T"}
    def complement_dna(nucleotides)
      nucleotides.each_char.inject("") { |rna, nucleotide| rna + DNA_COMPLEMENT[nucleotide] }
    end
 
    def complement_rna(nucleotides)
      nucleotides.each_char.inject("") { |dna, nucleotide| dna + RNA_COMPLEMENT[nucleotide] }
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
