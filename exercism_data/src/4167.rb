require 'forwardable'

class Hamming
  class << self
    # Returns the number of differences between two strands as an Integer
    def compute(first_sequence, second_sequence)
      shortest_strand = [first_sequence.size, second_sequence.size].min

      shortest_strand.times.count do |i|
        first_sequence[i] != second_sequence[i]
      end
    end
  end
end
