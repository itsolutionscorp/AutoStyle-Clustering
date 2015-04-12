class Series

  def initialize nums
    @nums = nums.chars.map(&:to_i)
  end

  def slices length
    raise ArgumentError if length > @nums.size
    (0..@nums.size-length).map { |idx| @nums[idx, length] }
  end
end