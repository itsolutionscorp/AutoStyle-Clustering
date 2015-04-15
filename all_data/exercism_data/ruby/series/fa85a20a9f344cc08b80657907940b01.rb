class Series
  attr_reader :digits
  def initialize num
    raise ArgumentError, 'Argument is not a string of numbers' unless num.scan(/[^0-9]/).empty?
    @digits = num.chars.map(&:to_i)
  end
  
  def slices num
    raise ArgumentError, 'slice_size is greater than the number of digits' unless num <= @digits.size

    return @digits.each_cons(num).to_a
  end
  
  def my_slices num
    raise ArgumentError, 'slice_size is greater than the number of digits' unless num <= @digits.size
    ret = Array.new
    @digits.each_with_index do |digit,index|
      arr = Array.new
      (0...num).each do |i|
        arr.push(@digits[index+i])
      end
      ret.push(arr) unless arr[-1] == nil
    end
    ret
  end
end
