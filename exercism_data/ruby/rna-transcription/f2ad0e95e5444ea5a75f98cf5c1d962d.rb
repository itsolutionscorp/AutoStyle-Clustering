module Complement
  NUCLEOTIDES_MAPPING = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  class << self
    def of_dna(nucleotides)
      translate(nucleotides, :fetch)
    end

    def of_rna(nucleotides)
      translate(nucleotides, :key)
    end

    private

    def translate(nucleotides, method)
      nucleotides.each_char.map do |nucleotide|
        NUCLEOTIDES_MAPPING.send(method, nucleotide)
      end.join
    end
  end
end
