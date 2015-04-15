class PhoneNumber

  attr_reader :number

  def initialize number
    number.delete! "^0-9"
    number[0] = '' if number.length == 11 && number[0] == "1"
    @number = number.length == 10 ? number : "0"*10
  end

  def area_code
    @number[0..2]
  end

  def to_s
    "(#{@number[0..2]}) #{@number[3..5]}-#{@number[6..9]}"
  end
end
