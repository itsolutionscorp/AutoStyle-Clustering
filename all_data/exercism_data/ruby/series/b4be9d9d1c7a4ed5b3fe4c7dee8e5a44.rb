class Series
  def initialize(nums)
    @nums = nums
  end

  def slices(slice_size)
    fail ArgumentError if slice_size > @nums.length
    nums = @nums.chars.map(&:to_i)
    Enumerator.new do |yielder|
      loop do
        break if nums.length < slice_size
        yielder.yield nums.take(slice_size)
        nums = nums.drop(1)
      end
    end.to_a
  end
end
