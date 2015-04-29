class PhoneNumber

  attr_accessor :str

  def initialize(str)
    @str = str
    @str = extract
    @str = ERROR_RETURN unless valid?
  end

  def number
    str[-10..-1]
  end

  def area_code
    str[0..2]
  end

  def to_s
    PHONE_FORMAT.zip(str[-10..-1].chars).compact.join
  end

  private
  def extract
    no_letters? ? str.scan(/\d+/).join : str
  end

  def valid?
    no_letters? && ( ten_digits? || eleven_digits_and_starts_with_1? )
  end

  def no_letters?
    str.scan(/[a-zA-Z]/).empty?
  end

  def ten_digits?
    str.length == 10
  end

  def eleven_digits_and_starts_with_1?
    str.length == 11 && str[0] == '1'
  end

  ERROR_RETURN = '0000000000'
  PHONE_FORMAT = ['(',nil,nil,') ',nil, nil, '-', nil, nil, nil, nil]

  # def extract_number(str)    
  #   str = str.scan(/\d+/).join
  #   return str[-10..-1] if str.length == 10 || str.length == 11 && str[0] == '1'
  #   return ERROR_RETURN
  # end

end
