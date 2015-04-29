class Series
  def initialize num_str
    @nums = num_str.chars.map(&:to_i)
  end

  def slices n # re-implemented .each_cons
    fail ArgumentError if n > @nums.length
    0.upto(@nums.length - n).each_with_object([]) do |i, a|
      a << 0.upto(n - 1).each_with_object([]) do |j, aa|
        aa << @nums[i + j]
      end
    end
  end
end
