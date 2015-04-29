class Complement
  class << self
    DNA_TO_RNA = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}  

    def of_dna(dna)
      calculate_complement(dna, DNA_TO_RNA)
    end

    def of_rna(rna)
      calculate_complement(rna, DNA_TO_RNA.invert)
    end

    private

    def calculate_complement(s, k)
      s.split("").map {|l| k[l]}.join
    end
  end
end
