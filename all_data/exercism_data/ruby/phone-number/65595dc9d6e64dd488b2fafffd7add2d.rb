class PhoneNumber
  attr_reader :number_string

  INVALID_NUMBER = ["0000000000"]
  
  def initialize(number_string)
    @number_string = number_string
  end

  def number
    digits.join
  end

  def digits
    digits = number_string.scan(/[[:digit:]]/)
    validate(digits)
  end

  def validate(digits)
    digits = remove_international_code(digits) if international_code?(digits)
        
    if digits.length > 10 || digits.length < 10
      INVALID_NUMBER
    else
      digits
    end
  end
  
  def regionalize(digits)
    if international_code?
      self.remove_international_code
    else
      digits
    end
  end

  def remove_international_code(digits)
    digits.drop(1)
  end

  def international_code?(digits)
    digits.first == "1" && digits.length == 11
  end
  
  def area_code
    digits.take(3).join
  end
  
  def exchange_number
    digits.drop(3).take(3).join
  end
  
  def subscriber_number
    digits.drop(6).join
  end
  
  def to_s
    "(#{area_code}) #{exchange_number}-#{subscriber_number}"
  end
end
