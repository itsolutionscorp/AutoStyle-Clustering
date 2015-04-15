class PhoneNumber
  def initialize(str)
    @str = str.gsub(/[^\d+]/,'')
  end

  def number
    return @str if @str.size == 10
    return @str.reverse.chop.reverse if @str.size == 11 && @str.chars.first.to_i == 1
    "0" * 10 if @str.size != 10
  end

  def area_code
    str = number
    str.slice(0,3)
  end

  def to_s
    str = number
    "(#{str[0,3]}) #{str[3,3]}-#{str[6,4]}"
  end

end
