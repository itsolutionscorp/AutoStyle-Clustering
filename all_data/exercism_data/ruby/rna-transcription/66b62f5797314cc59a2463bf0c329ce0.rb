class Complement

  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  class << self
    def of_dna(strands)
      complement(strands)
    end

    def of_rna(strands)
      complement(strands, DNA_TO_RNA.invert)
    end

    private

    def complement(strands, mapping = DNA_TO_RNA)
      strands.each_char.map do |x|
        mapping.fetch(x)
      end.join
    end
  end
end
