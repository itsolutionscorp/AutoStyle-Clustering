class PhoneNumber
  def initialize(raw)
    @raw = raw
  end

  def area_code
    number[0, 3]
  end
  
  def number
    @clean ||= clean
  end

  def to_s
    "(%d) %d-%g" % [area_code, number[3..5], number[6..9]]
  end

private

  def clean
    if correct_length?
      digits
    elsif international?
      localize
    else
      default_number
    end
  end
  
  def correct_length?
    digits.length == 10
  end
  
  def default_number
    '0000000000'
  end

  def digits
    @digits ||= @raw.scan(/\d+/).join
  end

  def international?
    digits.length == 11 && digits.start_with?('1')
  end

  def localize
    @localized ||= digits[1..10]
  end

end
