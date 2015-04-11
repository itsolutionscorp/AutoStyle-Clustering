class DNA
  def initialize(strand)
    @strand = strand
  end

  def nucleotide_counts
    @nucleotide_counts ||= count_nucleotides(strand)
  end

  def count(nucleotide)
    validate nucleotide
    nucleotide_counts.fetch(nucleotide, 0)
  end

  private
  attr_reader :strand

  def validate(letter)
    Nucleotides.valid? letter
  end

  def nucleotides
    strand.chars
  end

  def count_nucleotides(strand)
    nucleotides.each_with_object(Nucleotides.empty_dna) do |nucleotide, new_count|
      new_count[nucleotide] += 1
    end
  end
end

class Nucleotides
  attr_reader :initial

  ADENOSINE = 'A'
  CYTIDINE  = 'C'
  GUANOSINE = 'G'
  THYMIDINE = 'T'
  URACIL    = 'U'

  VALID_NUCLEOTIDES = [
    ADENOSINE,
    CYTIDINE,
    GUANOSINE,
    THYMIDINE,
    URACIL
  ]

  def self.empty_dna
    {
      ADENOSINE => 0,
      CYTIDINE  => 0,
      GUANOSINE => 0,
      THYMIDINE => 0
    }
  end

  def self.valid?(letter)
    raise ArgumentError unless VALID_NUCLEOTIDES.include? letter
  end
end
