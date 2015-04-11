class PhoneNumber
  attr_reader :number

  def initialize(str)
    @number = make_number(str)
  end

  def area_code
    @number[0..2]
  end

  def to_s
    "(#{area_code}) #{@number[3..5]}-#{@number[6..9]}"
  end

  private
  def make_number(str)
    num = str.gsub(/\D/, '')
    num = num[1..-1] if num.length == 11 && num[0] == "1"
    valid_digits = !str[/[A-z]/]
    valid_length = num.length == 10
    valid_length && valid_digits ? num : "0"*10
  end
end
