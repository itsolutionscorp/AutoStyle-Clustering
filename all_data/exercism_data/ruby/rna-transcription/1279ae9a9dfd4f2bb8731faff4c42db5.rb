class Complement
  DNA_TO_RNA_MAP = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  RNA_TO_DNA_MAP = DNA_TO_RNA_MAP.invert

  class << self
    def of_dna(sequence)
      convert_sequence sequence, DNA_TO_RNA_MAP
    end

    def of_rna(sequence)
      convert_sequence sequence, RNA_TO_DNA_MAP
    end

    private

    def convert_sequence(sequence, mapper)
      sequence.chars.map { |char| mapper[char] || raise(ArgumentError) }.join
    end
  end
end
