class PhoneNumber
  attr_reader :number
  BAD_NUMBER_FALLBACK = '0000000000'


  def initialize(number)
    @number = sanitize_number(number)
  end

  def area_code
    number[0...3]
  end

  def to_s
    # unfortunately, I do not know, how those blocks are called in phone numbers, that's why that weird variable name exist
    before_hyphen = number[3...6]
    rest = number[6..-1]
    sprintf("(%s) %s-%s", area_code(), before_hyphen, rest)
  end


  private

  def sanitize_number(number)
    number = number.gsub(/\W/, '') # number might be formatted (555) 124-4567, remove all special chars
      .sub(/^1(?=\d{10}$)/, '') # remove leading 1, if number is 11 in length and starts with 1
      .scan(/\A\d{10}\z/)[0] # check if number is now 10 in length and only contains digits

    return number if number

    BAD_NUMBER_FALLBACK
  end

end
