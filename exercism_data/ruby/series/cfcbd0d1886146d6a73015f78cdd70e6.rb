class Series
  def initialize str
    @str = str.chars
  end
  def slices n
    raise ArgumentError if n > @str.size
    @str.each_cons(n).map { |a| a.map &:to_i }
  end
end
