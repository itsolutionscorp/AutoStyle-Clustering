class Complement
  DNA_TO_RNA = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  class << self
    def of_dna(str)
      complement str, DNA_TO_RNA
    end

    def of_rna(str)
      complement str, DNA_TO_RNA.invert
    end

    private

    def complement(str, mapping)
      str.split("").map!{|c| mapping[c]}.join('')
    end
  end
end
