module Complement
  COMPLEMENT = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna)
    DNA.new(dna).complement
  end

  def self.of_rna(rna)
    RNA.new(rna).complement
  end

  DNA = Struct.new(:sequence) do
    def complement
      sequence.chars.map do |nucleotide|
        COMPLEMENT[nucleotide]
      end.join
    end
  end

  RNA = Struct.new(:sequence) do
    def complement
      sequence.chars.map do |nucleotide|
        COMPLEMENT.key(nucleotide)
      end.join
    end
  end
end
