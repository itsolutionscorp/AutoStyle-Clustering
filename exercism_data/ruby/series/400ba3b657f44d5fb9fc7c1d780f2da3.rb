class Series

  def initialize nums
    @nums = nums
  end

  def slices length 
    raise ArgumentError if @nums.length < length
    series = []
    (0..@nums.length - length).each do |index|
      series.push @nums[index, length].chars.map(&:to_i)
    end
    series
  end
end
