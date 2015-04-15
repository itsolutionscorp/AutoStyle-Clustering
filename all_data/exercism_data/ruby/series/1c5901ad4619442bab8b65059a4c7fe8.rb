class Series
  def initialize(string)
    @digits = extract_digits(string)
  end

  def slices(length)
    raise ArgumentError if length > @digits.length

    slice_starts(length).map do |start|
      @digits[start, length]
    end
  end

  private
  
  def extract_digits(string)
    string.chars.map(&:to_i)
  end
  
  def slice_starts(slice_length)
    0...(@digits.length + 1 - slice_length)
  end
end
