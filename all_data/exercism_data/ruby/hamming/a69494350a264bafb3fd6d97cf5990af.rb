class Hamming
  def self.compute strand_a, strand_b
    fail if strand_a.length != strand_b.length
    difference = 0
    strand_a.chars.each_with_index do |letter, i|
      letter == strand_b[i] ? true : difference += 1
    end
    difference
  end
end
