module Hamming
  module_function

  def compute(first, other)
    first_strand = Strand.new(first)
    other_strand = Strand.new(other)

    first_strand.compare(other_strand)
  end

  class Strand
    attr_reader :sequence
    protected :sequence

    def initialize(sequence)
      @sequence = sequence
    end

    def compare(other)
      return 0 if sequence.eql?(other.sequence)
      short, long = sort_strands(self, other)
      short_nucleotides = short.nucleotides[0..(long.length)]
      mutations = short_nucleotides.each_with_index.select do |nucleotide, i|
        !nucleotide.eql?(long.nucleotides[i])
      end

      mutations.length
    end

    def length
      nucleotides.length
    end

    protected

    def nucleotides(max_length = -1)
      @nucleotides ||= sequence.chars
      @nucleotides[0..max_length]
    end

    private

    def sort_strands(first, second)
      [first, second].sort_by(&:length)
    end
  end
end
