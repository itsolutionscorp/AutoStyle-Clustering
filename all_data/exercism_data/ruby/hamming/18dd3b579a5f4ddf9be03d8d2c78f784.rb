module Hamming

  def self.compute(original_strand, other_strand)
    StrandPair.new(original_strand, other_strand).hamming_distance
  end

  class StrandPair
    attr_accessor :original_strand
    attr_accessor :other_strand

    def initialize(original_strand, other_strand)
      self.original_strand = original_strand
      self.other_strand = other_strand
    end

    def hamming_distance
      nucleic_pairs.count(&:mutation?)
    end

    private

    def nucleic_pairs
      (0..common_length-1).map do |position|
        NucleicPair.new(original_strand[position], other_strand[position])
      end
    end

    def common_length
      [original_strand.length, other_strand.length].min
    end
  end

  NucleicPair = Struct.new(:original_nucleotide, :other_nucleotide) do
    def mutation?
      original_nucleotide != other_nucleotide
    end
  end

end
