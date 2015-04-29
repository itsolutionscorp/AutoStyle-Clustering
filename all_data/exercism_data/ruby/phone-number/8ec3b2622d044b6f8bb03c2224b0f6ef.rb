class PhoneNumber

  attr_accessor :string

  def initialize(str)
    @string = extract_number(str)
  end

  def number
    @string
  end

  def area_code
    @string[0..2]
  end

  def to_s
    PHONE_FORMAT.zip(@string.chars).compact.join
  end

  private
  def extract_number(string)    
    return ERROR_RETURN if string.scan(/[a-zA-Z]/).any?
    string = string.scan(/\d+/).join
    return string[-10..-1] if string.length == 10 || string.length == 11 && string[0] == '1'
    return ERROR_RETURN
  end

  ERROR_RETURN = '0000000000'
  PHONE_FORMAT = ['(',nil,nil,') ',nil, nil, '-', nil, nil, nil, nil]

end
