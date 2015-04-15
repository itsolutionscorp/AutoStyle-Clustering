class Hamming
  def self.compute(strand_a, strand_b)
    strand_b = strand_b.each_char.to_a
    i = 0
    strand_a.each_char.each_with_object([]) do |c, a|
      a.push(1) if c != strand_b[i] && !strand_b[i].nil?
      i += 1
    end.reduce(:+) || 0
  end
end
