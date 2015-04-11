class Trinary
  def initialize trinary
    @trinary = trinary
    @cached_value = 0 if /[^012]+/.match(trinary)
  end

  def to_decimal
    return @cached_value if @cached_value
    result = 0
    length = @trinary.length-1
    # Something kind of fun is that this loop works in either forwards or backwards direction
    # You could use either .each or .reverse_each with the same results. :)
    (0..length).each do |i| 
      result += @trinary[i].to_i*3**(length-i)
    end
    @cached_value = result
  end
end
