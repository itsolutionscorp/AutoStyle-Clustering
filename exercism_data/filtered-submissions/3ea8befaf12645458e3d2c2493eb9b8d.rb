class Hamming
  def compute strand_a, strand_b
    short, long = [strand_a, strand_b].sort_by { |s| s.length }
    short.each_char.count.with_index { |c, i| c != long[i] }
  end
end
