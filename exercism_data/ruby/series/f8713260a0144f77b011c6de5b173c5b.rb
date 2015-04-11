class Series
  attr_accessor :ds
  def initialize(digit_string)
    @ds = digit_string.split(//).map! { |c| c.to_i }
  end

  def slices(num)
    raise ArgumentError if num > ds.size
    ds.size.times.each_with_object([]) do |i, arr|
      arr << ds[i...i + num] unless num > 1 && ds.size < i + num
    end
  end
end
