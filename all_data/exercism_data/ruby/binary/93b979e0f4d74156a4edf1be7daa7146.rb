class Binary
  def initialize binary
    @binary = binary
    @cached_value = 0 if /[^01]+/.match(binary)
  end

  def to_decimal
    return @cached_value if @cached_value
    result = 0
    length = @binary.length-1
    # Something kind of fun is that this loop works in either forwards or backwards direction
    # You could use either .each or .reverse_each with the same results. :)
    (0..length).each do |i| 
      result += @binary[i].to_i*2**(length-i)
    end
    @cached_value = result
  end
end
