class PhoneNumber

  DEFAULT = "0000000000"

  attr_accessor :number

  def initialize(number)
    the_num = extract_number(number)
    if valid?( the_num )
      @number = the_num
    else
      @number = DEFAULT
    end
  end

  def area_code
    @number.slice(0,3)
  end

  def to_s
    "(#{@number.slice(0,3)}) #{@number.slice(3,3)}-#{@number.slice(6,4)}"
  end

  private
  def extract_number(number)
    word_chars = number.scan(/(\w+)/).join
    if full_us_number?(word_chars)
      extracted_num = word_chars[1..10]
    else
      extracted_num = word_chars
    end
    extracted_num
  end

  def full_us_number?(a_number)
    a_number.length == 11 && a_number[0] == "1"
  end

  def valid?(number)
    number && number.length == 10 && number.scan(/\D+/).length == 0
  end

end
