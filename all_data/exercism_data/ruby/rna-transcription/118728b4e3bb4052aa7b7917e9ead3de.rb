class Complement
  DNA_TO_RNA = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  class << self
    def of_dna(str)
      str.split("").map!{|c| DNA_TO_RNA[c]}.join('')
    end

    def of_rna(str)
      str.split("").map!{|c| DNA_TO_RNA.invert[c]}.join('')
    end
  end
end
