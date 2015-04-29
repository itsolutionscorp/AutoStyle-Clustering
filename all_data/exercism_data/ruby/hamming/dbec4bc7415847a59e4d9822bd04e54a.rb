class Hamming

  def self.compute(strand1, strand2)
    strand1 = strand1.chars
    strand2 = strand2.chars
      count = 0
      strand1.take(strand2.length).each_with_index do |base, i|
    if base != strand2[i]
      count += 1
    end
  end
      count
  end
end
