class Series
  def initialize(num_str)
    @nums = num_str.chars.map { |x| x.to_i }
  end

  # # built-in
  # def slices(n)
  #   fail ArgumentError if n > @nums.size
  #   @nums.each_cons(n).to_a
  # end

  # re-implemented
  def slices(n)
    fail ArgumentError if n > @nums.length
    0.upto(@nums.length - n).each_with_object([]) do |i, a|
      a << 0.upto(n - 1).each_with_object([]) do |j, aa|
        aa << @nums[i + j]
      end
    end
  end
end
