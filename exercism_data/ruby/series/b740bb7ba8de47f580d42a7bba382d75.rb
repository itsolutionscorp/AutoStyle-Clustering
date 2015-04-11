class Series
  def initialize(seq)
    @seq = seq.chars.map(&:to_i)
  end

  def slices(n)
    raise ArgumentError.new if n > @seq.size
    (@seq.size - n + 1).times.map { @seq.first(n).tap { @seq.push(@seq.shift) } }
  end
end
