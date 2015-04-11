class Complement
  DNA_RNA = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_DNA = DNA_RNA.invert

  class << self
    def of_dna(original)
      original.chars.map { |letter| DNA_RNA[letter] }.join
    end

    def of_rna(original)
      original.chars.map { |letter| RNA_DNA[letter] }.join
    end
  end
end
