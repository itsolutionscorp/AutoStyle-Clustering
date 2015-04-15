module Complement
  class << self
    DNA_TO_RNA = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
    RNA_TO_DNA = DNA_TO_RNA.invert

    def of_dna(sequence)
      map_with(sequence, DNA_TO_RNA)
    end

    def of_rna(sequence)
      map_with(sequence, RNA_TO_DNA)
    end

    private

    def map_with(sequence, mappping)
      sequence.chars.map { |char| mappping[char] }.join
    end
  end
end
