class Complement
  class Strand < Struct.new(:strand, :type)   
    def complement
      type == "dna" ? "rna" : "dna"
    end
    
    def invert
      strand.chars.map{|nucleotide| ComplementPermutation.send("#{type}_to_#{complement}", nucleotide) }.join("")
    end
  end
  
  class ComplementPermutation
    def self.base_replacement_matrix
      {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
    end
    
    def self.dna_to_rna(nucleotide)
      base_replacement_matrix[nucleotide]
    end

    def self.rna_to_dna(nucleotide)
      base_replacement_matrix.invert[nucleotide]
    end
  end
  
  %w[dna rna].each do |source_type|
    define_singleton_method("of_#{source_type}") do |strand|
      Strand.new(strand, source).invert
    end
  end
  
end
