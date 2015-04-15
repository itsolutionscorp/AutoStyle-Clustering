class Hexadecimal
  def initialize hexadecimal
    @hexadecimal = hexadecimal
    @cached_value = 0 if /[^0-9^a-f]+/.match(hexadecimal)
  end

  def to_decimal
    return @cached_value if @cached_value
    result = 0
    length = @hexadecimal.length-1
    # Something kind of fun is that this loop works in either forwards or backwards direction
    # You could use either .each or .reverse_each with the same results. :)
    (0..length).each do |i| 
      result += value(@hexadecimal[i])*16**(length-i)
    end
    @cached_value = result
  end

  private

  def value n
    {
      "0" => 0,
      "1" => 1,
      "2" => 2,
      "3" => 3,
      "4" => 4,
      "5" => 5,
      "6" => 6, 
      "7" => 7, 
      "8" => 8,
      "9" => 9,
      "a" => 10,
      "b" => 11,
      "c" => 12,
      "d" => 13,
      "e" => 14,
      "f" => 15
    }[n]
  end
end
