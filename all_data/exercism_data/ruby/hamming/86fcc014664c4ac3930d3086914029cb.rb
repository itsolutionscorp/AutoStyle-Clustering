class Hamming
  def self.compute(strand, another_strand)
    count = 0
    (0...(strand.length)).each do |i|
      count += 1 unless strand[i] == another_strand[i]
    end
    count
  end
end
