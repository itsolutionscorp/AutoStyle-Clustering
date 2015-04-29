class Series

  def initialize nums
    @nums = nums
  end

  def slices length 
    series = []
    (0..@nums.length - length).each do |index|
      series.push(@nums[index, length].chars.map(&:to_i))
    end
    series.empty? ? (raise ArgumentError) : series
  end
end
