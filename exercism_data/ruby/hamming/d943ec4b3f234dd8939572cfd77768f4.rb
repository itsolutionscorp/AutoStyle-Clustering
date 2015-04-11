class Hamming
  def self.compute strand_a, strand_b
    short, long = [strand_a, strand_b].sort { |a, b| a.length <=> b.length }
    short.each_char.count.with_index { |c, i| c != long[i] }
  end
end
