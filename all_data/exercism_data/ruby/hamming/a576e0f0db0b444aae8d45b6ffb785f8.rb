class Hamming

  def self.compute(strand1, strand2)
    strand1 = strand1.scan(/./)
    strand2 = strand2.scan(/./)
    count ||= 0

    strand1.each_with_index do |char, i|
      count += 1 unless char == strand2[i] unless strand2[i] == nil
    end
      count
  end
end
