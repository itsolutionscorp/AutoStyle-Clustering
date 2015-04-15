class Series

  attr_reader :numbers

  def initialize(n)
    @numbers = n.chars.map(&:to_i)
  end

  def slices(n)
    raise ArgumentError if n > numbers.length
    numbers.each_with_index.map { |l, i| substring(i, n) }
  end

  private
  def substring(i, n)
    numbers[i...(i+n)] if numbers[i...(i+n)].length == n
  end

end
