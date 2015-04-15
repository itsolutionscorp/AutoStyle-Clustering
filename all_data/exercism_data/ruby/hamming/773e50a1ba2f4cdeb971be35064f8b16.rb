class Hamming
  def self.compute(first_strand, second_strand)
    hamming = 0
    (0..get_shortest_length(first_strand,second_strand)-1).each do |letter|
      hamming += 1 unless first_strand[letter] == second_strand[letter]
    end
    hamming
  end

  def self.get_shortest_length(first_strand, second_strand)
   first_strand.size < second_strand.size ? first_strand.size : second_strand.size
  end
end
