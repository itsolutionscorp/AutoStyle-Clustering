class PhoneNumber
  BLANK_NUM = '0000000000'

  def initialize(text)
    @number = validate(text)
  end

  def number
    @number
  end

  def area_code
    @number[0..2]
  end

  def to_s
    "(#{area_code}) #{@number[3..5]}-#{@number[6..9]}"
  end

  def validate(text)
    return BLANK_NUM if text[/[a-zA-Z]/]
    t = text.scan(/\d+/).inject(:+)
    return t[1..-1] if t.length == 11 && t[0] == '1'
    t.length == 10 ? t : BLANK_NUM
  end
end
