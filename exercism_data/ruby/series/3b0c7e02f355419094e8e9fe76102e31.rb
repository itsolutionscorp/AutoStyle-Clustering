class Series
  def initialize(str)
    @str = str
  end

  def slices(n)
    raise ArgumentError if n > @str.length

    (0..@str.length - n).map { |i| @str[i..i+n-1].split("").map(&:to_i) }
  end
end
