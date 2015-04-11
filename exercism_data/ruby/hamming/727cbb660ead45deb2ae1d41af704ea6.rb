module Hamming
  def self.compute(first_strand, second_strand)
    result = 0
    [first_strand.length, second_strand.length].min.times do |index|
      result += 1 if first_strand[index] != second_strand[index]
    end
    result
  end
end
