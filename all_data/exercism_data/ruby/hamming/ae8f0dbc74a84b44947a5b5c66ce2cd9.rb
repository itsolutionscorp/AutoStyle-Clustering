module DNA
  class Strand
    attr_accessor :sequence

    def initialize(sequence)
      @sequence = sequence.chars
    end

    def hamming_distance_from(other)
      sequence_1, sequence_2 = truncate_sequences(sequence, other.sequence)

      distance = 0
      sequence_1.each_with_index do |item, index|
        distance += 1 if item != sequence_2[index]
      end
      distance
    end

    private
    def truncate_sequences(sequence_1, sequence_2)
      max_length = [ sequence_1.count, sequence_2.count ].min
      return sequence_1.take(max_length), sequence_2.take(max_length)
    end
  end
end

class Hamming
  def self.compute(sequence_1, sequence_2)
    strand_A = DNA::Strand.new(sequence_1)
    strand_B = DNA::Strand.new(sequence_2)

    strand_A.hamming_distance_from(strand_B)
  end
end
