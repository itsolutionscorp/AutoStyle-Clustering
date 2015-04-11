class Trinary
  def initialize number
    @number = number
  end
  
  def to_decimal
    @decimal ||= sum_of_digit_values
  end
  
  private
  def each_digit
    @number.chars.reverse.map
  end
  
  def sum_of_digit_values
    each_digit.with_index{|digit, i| digit_value digit, i }.inject(:+)
  end
  
  def digit_value digit, position
    digit.to_i * 3**position
  end
end
