module NumberConversion

  def to_decimal
    return 0 unless valid?
    sum = 0
    digit_list.reverse.each_with_index do |digit, index|
      sum += digit.to_i * base**index.to_i
    end
    sum
  end

  private 

  def digit_list
    digits.chars
  end

  def digits
    raise NotImplementedError
  end
  
  def base
    raise NotImplementedError
  end

  def valid?
    raise NotImplementedError
  end

end

class Binary 
  include NumberConversion

  attr_reader :digits

  def initialize(digits)
    @digits = digits
  end

  def valid?
    digits =~ /^[0|1]+$/
  end

  def base
    2
  end
end
