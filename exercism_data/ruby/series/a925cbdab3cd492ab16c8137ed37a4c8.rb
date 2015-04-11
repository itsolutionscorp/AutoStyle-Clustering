class Series
  def initialize(s)
    @s = s
  end

  def slices(length)
  	raise ArgumentError if length > @s.length
    (0..@s.length - length).each_with_object([]) { |idx, arr| arr << subset(idx, length) }
  end

  :private
  
  def subset(start, length)
  	@s.slice(start, length).chars.map(&:to_i)
  end
end
