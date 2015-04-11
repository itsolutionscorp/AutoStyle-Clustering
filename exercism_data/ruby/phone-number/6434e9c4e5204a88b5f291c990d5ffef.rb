class PhoneNumber
  
  attr_accessor :number

  NON_NUMERIC       = %r{\D}
  AREA_CODE_LENGTH  = 3

  
  def initialize(number)
    @number = number
    clean_number
  end

  def area_code
    number[0..AREA_CODE_LENGTH-1]
  end

  def to_s
    "(#{area_code}) #{location_code}"
  end

  private

  def location_code
    "#{number[AREA_CODE_LENGTH..5]}-#{number[6..-1]}"
  end

  def clean_number
    remove_non_numeric_characters
    trim_to_length
  end

  def remove_non_numeric_characters
    @number.gsub!(NON_NUMERIC,"")
  end

  def trim_to_length(length = 10)
    @number = number[1..-1] if number.length == 11 && number[0] == "1"
    @number = "0"*10        unless number.length == length
  end

end
