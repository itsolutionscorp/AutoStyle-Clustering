class PhoneNumber
  BAD_NUMBER_FALLBACK = '0000000000'

  def initialize(number)
    @number = number
  end

  def number
    begin
      number = sanitize_number(@number)
    rescue
      BAD_NUMBER_FALLBACK
    end
    return BAD_NUMBER_FALLBACK unless number

    number
  end

  def area_code
    number[0...3]
  end

  def to_s
    number = number()
    # unfortunately, I do not know, how those blocks are called in phone numbers, that's why that weird variable name exist
    before_hyphen = number[3...6]
    rest = number[6..-1]
    sprintf("(%s) %s-%s", area_code(), before_hyphen, rest)
  end


  private

  def sanitize_number(number)
    # number might be formatted, remove all possible delimiters
    number = number.gsub(/[\s()-.]/, '')

    # get number if it's between 10 and 11 chars in length, nil otherwise
    number = number.scan(/^\d{10,11}$/)[0]

    unless number
      raise ArgumentError('The number length is neither 10 nor 11')
    end

    if number.length === 11
      unless number[0] === '1'
        raise ArgumentError('number length is 11, but first digit is not 1')
      end
      number = number[1..-1]
    end

    number
  end

end
