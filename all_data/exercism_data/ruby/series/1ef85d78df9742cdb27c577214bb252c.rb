class Series
  def initialize(string)
    @sequence = string.chars.map(&:to_i)
  end

  def slices(size)
    raise ArgumentError if size > @sequence.length
    slices = []

    @sequence.each_with_index do |num, index|
      end_pos = index + size
      slices << @sequence[index...end_pos] unless end_pos > @sequence.length
    end

    slices
  end
end
