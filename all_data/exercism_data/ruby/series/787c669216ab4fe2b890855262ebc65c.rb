class Series
  def initialize(str)
    @digits = str.chars.map(&:to_i)
  end

  def slices(length)
    e_msg = 'Slices cannot be longer than the original string'
    fail ArgumentError, e_msg if nb_slices(length) < 1
    (0...nb_slices(length)).map { |offset| slice offset, length }
  end

  private

  def nb_slices(length)
    @digits.length - length + 1
  end

  def slice(offset, length)
    @digits[offset...(offset + length)]
  end
end
