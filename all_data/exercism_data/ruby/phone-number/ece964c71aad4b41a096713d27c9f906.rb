# class PhoneNumber
class PhoneNumber
  attr_accessor :str
  def initialize(str)
    @str = str.gsub(/[^0-9]/, '')
    @str = '0000000000' unless valid?
  end

  def number
    return str[1..10] if str.length == 11 && str[0] == '1'
    str
  end

  def area_code
    number[0..2]
  end

  def valid?
    (str.match(/[0-9]{10}/)) &&
    (str.length == 10 || (str.length == 11 && str[0] == '1'))
  end

  def to_s
    n = number
    "(#{n[0..2]}) #{n[3..5]}-#{n[6..9]}"
  end
end
