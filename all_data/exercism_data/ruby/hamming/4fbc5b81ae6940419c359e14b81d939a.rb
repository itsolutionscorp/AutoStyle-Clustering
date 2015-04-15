module Hamming
  class Strand
    attr_reader :sequence
    protected :sequence

    def initialize(sequence)
      @sequence = sequence
    end

    def compare(other)
      return 0 if sequence.eql?(other.sequence)

      mutations =
        if length < other.length
          nucleotides(other.length).each_with_index.select do |nucleotide, i|
            !nucleotide.eql?(other.nucleotides[i])
          end
        elsif length > other.length
          other.nucleotides(length).each_with_index.select do |nucleotide, i|
            !nucleotide.eql?(nucleotides[i])
          end
        else
          nucleotides.each_with_index.select do |nucleotide, i|
            !nucleotide.eql?(other.nucleotides[i])
          end
        end

      mutations.length
    end

    protected

    def length
      nucleotides.length
    end

    def nucleotides(max_length = -1)
      @nucleotides ||= sequence.chars
      @nucleotides[0..max_length]
    end
  end

  module_function

  def compute(first, other)
    first_strand = Strand.new(first)
    other_strand = Strand.new(other)

    first_strand.compare(other_strand)
  end
end
