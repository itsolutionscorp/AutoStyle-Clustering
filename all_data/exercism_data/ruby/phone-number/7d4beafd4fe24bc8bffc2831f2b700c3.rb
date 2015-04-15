class PhoneNumber

  attr_reader :number

  def initialize(phone)
    @number = parse(phone)
  end

  def to_s
     "(#{area_code}) #{exchange}-#{subscriber}"
  end

  def area_code
    number.slice(0,3)
  end

  def exchange
    number.slice(3,3)
  end

  def subscriber
    number.slice(6,4)
  end


  private

  def invalid
    '0'*10
  end

  def parse(string)

    return invalid if string =~ /[A-Za-z]/

    string.gsub!(/[^0-9]/,"")

    string = string[1..-1] if (string.length > 10 && string[0] == '1')
    string = invalid if string.length != 10
    string
  end

end
