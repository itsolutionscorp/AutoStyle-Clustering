class Hamming
  class << self
    def compute(first_strand, second_strand)
      distance = 0
      [first_strand.size, second_strand.size].min.times do |i|
        distance +=1 unless first_strand[i] == second_strand[i]
      end
      distance
    end
  end
end
