class PhoneNumber
  attr_reader :number
  def initialize(number)
    @number = convert_bad_num(trim(strip(number)))
  end

  def area_code
    @number[0..2]
  end

  def to_s
    "(#{@number[0..2]}) #{@number[3..5]}-#{@number[6..9]}"
  end

  def strip(number)
    number.gsub(/[\(\)\s\-\.]/, '')
  end

  def trim(number)
    number.slice!(0) if number.length == 11 && number[0] == "1"
    number
  end

  def convert_bad_num(number)
    number = "0000000000" unless number =~ /^\d{10}$/
    number
  end
end
