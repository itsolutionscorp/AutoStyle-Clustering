class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def nucleotide_counts
    @nucleotide_counts ||= count_nucleotides
  end

  def count(nucleotide)
    raise ArgumentError unless Nucleotides.valid? nucleotide
    nucleotide_counts.fetch(nucleotide, 0)
  end

  private

  def strand_nucleotides
    strand.chars
  end

  def count_nucleotides
    strand_nucleotides.each_with_object(empty_count) do |nucleotide, new_count|
      new_count[nucleotide] += 1
    end
  end

  def empty_count
    Hash[Nucleotides.dna.zip([0].cycle)]
  end
end

module Nucleotides
  VALID_NUCLEOTIDES = [
    ADENOSINE = 'A',
    CYTIDINE  = 'C',
    GUANOSINE = 'G',
    THYMIDINE = 'T',
    URACIL    = 'U'
  ]

  def self.dna
    VALID_NUCLEOTIDES.select { |nucleotide| nucleotide != URACIL }
  end

  def self.valid?(letter)
    VALID_NUCLEOTIDES.include? letter
  end
end
