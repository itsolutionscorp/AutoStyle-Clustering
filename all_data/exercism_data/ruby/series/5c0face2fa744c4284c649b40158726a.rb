class Series
  attr_reader :string

  def initialize(string)
    @string = string
  end

  def slices(length)
    if length > string.length
      fail ArgumentError, 'length must not be greater than string length'
    end

    (0..string.length - length).map do |i|
      string[i, length].chars.map(&:to_i)
    end
  end
end
