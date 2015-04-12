class Hamming
  class << self
    def compute(first_strand, second_strand)
      puts "\n"
      strand1 = first_strand.chars
      strand2 = second_strand.chars
      shortest_strand_size = strand1.size <= strand2.size ? strand1.size : strand2.size
      distance = 0
      (0...shortest_strand_size).each do |i|
        distance +=1 unless strand1[i] == strand2[i]
      end
      distance
    end
  end
end
