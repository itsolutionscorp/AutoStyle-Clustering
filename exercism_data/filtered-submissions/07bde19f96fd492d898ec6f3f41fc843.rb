class Hamming
  def compute(strand, other)
    strand.chars.zip(other.chars).map(&:compact).map(&:uniq).reject{ |i| i.length == 1 }.length
  end
end
