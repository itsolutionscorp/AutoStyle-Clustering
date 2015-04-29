class Series
  def initialize(s)
    @s = s.chars.map(&:to_i)
  end

  def slices(n)
    raise ArgumentError.new if n > @s.size
    (@s.size-n+1).times.map { @s.first(n).tap { @s.push(@s.shift) } }
  end
end
