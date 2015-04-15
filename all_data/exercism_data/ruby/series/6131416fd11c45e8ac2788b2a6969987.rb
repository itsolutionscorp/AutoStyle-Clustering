class Series
  def initialize(string)
    @nums = string.chars.map(&:to_i)
  end

  def slices(slice_size)
    fail ArgumentError if slice_size > @nums.length
    make_slices(slice_size, @nums, [])
  end

  private

  def make_slices(slice_size, input, output)
    return output if input.length < slice_size
    make_slices(slice_size, input.drop(1), output.push(input.take(slice_size)))
  end
end
