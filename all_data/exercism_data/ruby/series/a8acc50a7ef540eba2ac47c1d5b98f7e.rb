class Series
  def initialize (str)
    @str = str
  end
  def slices (n)
    raise ArgumentError if n > @str.size
    @str.chars.map(&:to_i).each_cons(n).to_a
  end
end
