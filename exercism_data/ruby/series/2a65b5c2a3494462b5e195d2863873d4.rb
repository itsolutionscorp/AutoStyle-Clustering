class Series
  def initialize(string)
    @string = string
  end

  def slices(n)
    raise ArgumentError if n > string.length
    (0..(string.length - n)).map do |i|
      digits[i..(i + n - 1)]
    end
  end

  private

  attr_reader :string

  def digits
    @digits ||= string.chars.map(&:to_i)
  end
end
