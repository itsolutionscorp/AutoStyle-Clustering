class Hamming
  def self.compute(first_strand, second_strand)
    distances(first_strand, second_strand).reduce(:+)
  end

  def self.distances(first_strand, second_strand)
    first_strand.size.times.map do |position|
      (first_strand[position] <=> second_strand[position]).abs
    end
  end
end
