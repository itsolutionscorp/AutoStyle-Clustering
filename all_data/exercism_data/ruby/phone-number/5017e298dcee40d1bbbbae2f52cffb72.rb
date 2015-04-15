class PhoneNumber
  def initialize(number)
    @number = clean_number(number)
  end
  
  def number
    return '0000000000' unless valid?(@number)
    @number
  end

  def area_code
    @number[0..2]
  end

  def to_s
    "(#{area_code}) #{@number[3..5]}-#{@number[6..9]}"
  end

  private

  def clean_number(number)
    num = number.gsub(/[\(\)\-\.\s]/, '')

    if num_digits(num, 11) && first_digit_is_one(num)
      num[1..-1]
    else
      num
    end
  end

  def valid?(number)
    return true if num_digits(number, 10)
    return true if (num_digits(number, 11) && first_digit_is_one(number))
    false
  end

  def num_digits(number, digits)
    number.length == digits
  end

  def first_digit_is_one(number)
    number[0] == '1'
  end
end
