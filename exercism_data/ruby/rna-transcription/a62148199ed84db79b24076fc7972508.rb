class Strand
  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence
  end

  def complement
    sequence.each_char.each_with_object('') do |nucleotide, memo|
      memo << complements[nucleotide]
    end
  end

  def nucleotides
    raise NotImplementedError
  end

  private

  def complements
    {
      nucleotides[2] => nucleotides[1],
      nucleotides[1] => nucleotides[2],
      nucleotides[3] => nucleotides[0],
      nucleotides[0] => nucleotides[4]
    }
  end
end

class DNAStrand < Strand
  def nucleotides
    %w(A C G T U)
  end
end

class RNAStrand < Strand
  def nucleotides
    %w(A C G U T)
  end
end

class Complement
  class << self
    def of_dna(sequence)
      DNAStrand.new(sequence).complement
    end

    def of_rna(sequence)
      RNAStrand.new(sequence).complement
    end
  end
end
