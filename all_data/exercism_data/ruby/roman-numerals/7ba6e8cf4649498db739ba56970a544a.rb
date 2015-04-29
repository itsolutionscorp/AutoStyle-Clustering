require 'pry'

class Fixnum
  def to_roman
    result_string = ''
    num_vector = self.to_vector
    current_digit = ['M',  'C', 'X', 'I']
    half_digit    = ['NA', 'D', 'L', 'V']
    next_digit    = ['NA', 'M', 'C', 'X']

    4.times do |i|
      result_string += letterize(num_vector[i], current_digit[i], half_digit[i], next_digit[i] )
    end
    result_string
  end

  #protected

  def to_vector
    result = Array.new(4, 0)
    num_str = self.to_s.reverse
    [3, 2, 1, 0].each do |i|
      if num_str[i]
        result[i] = num_str[i].to_i
      end
    end
    result.reverse
  end

  def letterize(n, current_digit, half_digit, next_digit)
    case n
    when 0
      result = ''
    when 1..3
      result = current_digit * n
    when 4
      result = "#{current_digit}#{half_digit}"
    when 5
      result = half_digit
    when 6..8
      result =  half_digit + current_digit * (n - 5)
    when 9
      result = "#{current_digit}#{next_digit}"
    end
    result
  end

end
