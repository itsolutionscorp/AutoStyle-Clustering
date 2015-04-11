class PhoneNumberNormalizer
  DEFAULT_NUMBER = "0000000000".chars().to_a

  def initialize(raw_number)
    @numbers = raw_number.chars().to_a
  end

  def number
    remove_non_number
    trim_leading_one
    default_if_wrong_length
    @numbers.join('')
  end

  private
  def remove_non_number
    @numbers = @numbers.reject { |digit| /\d/.match(digit).nil?}
  end

  def trim_leading_one
    if (@numbers.size == 11 && @numbers[0] == '1')
      @numbers = @numbers[1..-1]
    end
  end

  def default_if_wrong_length
    if (@numbers.size != 10)
      @numbers = DEFAULT_NUMBER 
    end
  end
end

class PhoneNumber
  def initialize(raw_number)
    @number = PhoneNumberNormalizer.new(raw_number).number
  end

  def number
    @number
  end

  def area_code
    @number[0..2]
  end

  def exchange
    @number[3..5]
  end

  def subscriber
    @number[6..9]
  end

  def to_s
    "(" + area_code + ") " + exchange + "-" + subscriber
  end
end
