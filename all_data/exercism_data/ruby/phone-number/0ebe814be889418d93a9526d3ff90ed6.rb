class PhoneNumber

  attr_reader :number

  def initialize(str)
    @number = clean(str)
  end

  def area_code
    number[0..2]
  end

  def body
    "#{number[3..5]}-#{number[6..-1]}"
  end

  def to_s
    "(#{area_code}) #{body}"
  end

  private
  def clean(str)
    num = str.to_s.gsub(/[^\d]/, '')
    num = num[1..-1] if num.length == 11 && num[0] == "1"
    num = "0000000000" if num.length !=10

    num
  end

end
