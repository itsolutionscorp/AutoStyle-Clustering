class Hamming

  def self.compute(strand1, strand2)
    strand1 = strand1.chars
    strand2 = strand2.chars
    count = 0
    strand1.take(strand2.length).each_with_index do |s, i|
      count += 1 unless strand1[i] == strand2[i]
    end
    count
  end
end
