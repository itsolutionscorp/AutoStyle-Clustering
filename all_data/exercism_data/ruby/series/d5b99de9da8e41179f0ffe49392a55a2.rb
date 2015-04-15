class Series
  def initialize str
    @str = str.chars.map &:to_i
  end
  def slices n
    raise ArgumentError if n > @str.size
    @str.each_cons(n).to_a
  end
end
