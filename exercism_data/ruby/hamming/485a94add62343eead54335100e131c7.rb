class Hamming
  def self.compute(sequence1, sequence2)
    StrandPair.new(sequence1, sequence2).hamming_distance
  end

  StrandPair = Struct.new(:sequence, :other_sequence) do

    def hamming_distance
      nucleic_pair.count(&:mutation?)
    end

    def nucleic_pair
      (0...common_length).map do |i|
        NucleicPair.new(sequence[i], other_sequence[i])
      end
    end

    def common_length
      [sequence.length, other_sequence.length].min
    end

  end

  NucleicPair = Struct.new(:original_nucleotide, :other_nucleotide) do
    def mutation?
      !original_nucleotide.eql?(other_nucleotide)
    end
  end
end
