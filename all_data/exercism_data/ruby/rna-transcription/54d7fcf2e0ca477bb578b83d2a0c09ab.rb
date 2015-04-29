class Complement < Struct.new(:nucleotides)
  def self.of_dna(strand)
    DNAComplement.new(strand.chars).of_strand
  end

  def self.of_rna(strand)
    RNAComplement.new(strand.chars).of_strand
  end

  def of_strand
    complements = nucleotides.map{ |nucleotide| nucleotide_complement(nucleotide) }
    complements.join
  end

  def nucleotide_complement(nucleotide)
    {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => adenine_complement,
      'U' => 'A'
    }[nucleotide]
  end

  class DNAComplement < Complement
    def adenine_complement
      'U'
    end
  end

  class RNAComplement < Complement
    def adenine_complement
      'T'
    end
  end
end
