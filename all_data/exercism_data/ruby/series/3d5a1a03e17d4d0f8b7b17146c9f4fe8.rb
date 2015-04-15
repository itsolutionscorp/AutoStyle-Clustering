class Series
  def initialize(s)
    @s = s
  end

  def slices(n)
    raise ArgumentError if n > @s.length
    0.upto(@s.length - n).each_with_object([]) do |i, res|
      res << @s[i...i + n].chars.map { |c| c.to_i }
    end
  end
end
