class Hamming
  def self.compute(strand, mutated_strand)
    count = 0
    strand.length.times { |index| count += 1 unless strand[index] == mutated_strand[index] }

    count
  end
end