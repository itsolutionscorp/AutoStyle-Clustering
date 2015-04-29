class PhoneNumber
  def initialize(raw_number)
    @number = normalize(raw_number)
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

  private
  def normalize(raw_number)
    validate_length(trim_starting_one(remove_non_number(raw_number))).join('')
  end

  def remove_non_number(raw_number)
    raw_number.chars.reject { |digit| /[0123456789]/.match(digit).nil?}
  end

  def trim_starting_one(numbers)
    if (numbers.size == 11 && numbers[0] == '1')
      numbers[1..-1]
    else
      numbers
    end
  end

  def validate_length(numbers)
    if (numbers.size == 10)
      numbers
    else
      "0000000000".split('')
    end
  end
end
