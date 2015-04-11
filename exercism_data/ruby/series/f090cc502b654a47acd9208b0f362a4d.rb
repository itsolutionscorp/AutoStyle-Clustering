class Series
  def initialize(string)
    @string = string.chars.map(&:to_i)
  end

  def slices(size)
    raise ArgumentError if size > @string.length
    slices = []

    @string.each_with_index do |num, index|
      end_pos = index + size
      slices << @string[index...end_pos] unless end_pos > @string.length
    end

    slices
  end
end
