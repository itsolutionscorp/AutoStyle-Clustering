class Series
  def initialize(string)
    @string = string
  end

  def slices(length)
    raise ArgumentError, 'Slice is too long' if length > @string.length

    (0..@string.length - length).reduce([]) do |res, i|
      res << @string[i, length].chars.map(&:to_i)
    end
  end
end
