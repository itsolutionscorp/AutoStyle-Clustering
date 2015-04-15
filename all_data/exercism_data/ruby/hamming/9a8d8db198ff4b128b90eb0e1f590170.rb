class Hamming
  def self.compute(strand1, strand2)
    strand1, strand2 = truncate_to_same_length(strand1, strand2)
    distance = 0
    strand1.chars.each_with_index do |base, nucleotide|
      distance += 1 if base != strand2[nucleotide]
    end
    distance
  end

  def self.truncate_to_same_length(strand1, strand2)
    strand1 = strand1.to_str
    strand2 = strand2.to_str
    length = [strand1.length, strand2.length].min
    strand1 = strand1[0...length]
    strand2 = strand2[0...length]
    [strand1, strand2]
  end
end
