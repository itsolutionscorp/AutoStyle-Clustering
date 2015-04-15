class Series

  def initialize(string)
    @string = string
  end

  def slices(size)
    raise ArgumentError if size > @string.length
    (0..@string.length-size).map { |i| @string[i,size].split('').map(&:to_i) }
  end

end
