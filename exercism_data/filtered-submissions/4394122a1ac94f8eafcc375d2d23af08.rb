class Hamming
  class << self
    # Returns the number of differences between two strands as an Integer
    def compute(first_strand, second_strand)
      shortest_strand = [first_strand.size, second_strand.size].min

      shortest_strand.times.count { |i| first_strand[i] != second_strand[i] }
    end
  end
end
