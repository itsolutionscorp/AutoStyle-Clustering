class PhoneNumber
  BLANK = "0" * 10
  def initialize number
    @number = validate number
  end

  def number
    @number
  end

  def area_code
    @number[0..2]
  end

  def to_s
    "(#{area_code}) #{@number[3..5]}-#{@number[6..-1]}"
  end

  def validate number
    return BLANK if number[/[:alpha:]/]
    n = number.gsub(/[^0-9]/, "")
    return n[1..-1] if n.length == 11 and n[0] == "1"
    n.length == 10 ? n : BLANK
  end
end
