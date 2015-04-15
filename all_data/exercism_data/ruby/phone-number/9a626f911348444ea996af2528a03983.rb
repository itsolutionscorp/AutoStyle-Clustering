class Phone
  attr_reader :number

  def initialize(number)
    @number = sanitize(number)
  end

  def to_s
    "(#{area_code}) #{prefix}-#{suffix}"
  end

  def area_code
    number[0..2]
  end

  def prefix
    number[3..5]
  end

  def suffix
    number[6..9]
  end

  private

  def sanitize(number)
    clean = number.gsub(/[^0-9]/,"")
    return clean[-10..-1] if valid?(clean)
    return "0000000000"
  end

  def valid?(number)
    number.length == 10 || (number.length == 11 && number[0] == "1")
  end

end
