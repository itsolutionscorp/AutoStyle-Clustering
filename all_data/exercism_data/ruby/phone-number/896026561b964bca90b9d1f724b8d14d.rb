class PhoneNumber
  attr_reader :area_code, :number

  def initialize(phone_number)
    @number = cleanup_number(phone_number)
    @area_code = @number.chars.take(3).join
  end
  
  def to_s
    "(%s) %s-%s" % [@number[0,3], @number[3,3], @number[6..-1]]
  end

  private

  def cleanup_number(number)
    clean = number.scan(/^\D?(\d{3})\D?\s?(\d{3})\D?(\d{4,5})$/).join
    return "0000000000" unless clean =~ /^1?\d{10}$/

    clean.length == 11 ? clean[1..-1] : clean
  end
end
