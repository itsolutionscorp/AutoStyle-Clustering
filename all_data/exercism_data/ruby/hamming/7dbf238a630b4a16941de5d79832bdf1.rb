class Hamming
  def self.compute(first_strand, second_strand)
    minimum_strand_size = [first_strand.size, second_strand.size].min

    distance = 0
    minimum_strand_size.times do |index|
      distance += 1 if first_strand[index] != second_strand[index]
    end

    distance
  end
end
