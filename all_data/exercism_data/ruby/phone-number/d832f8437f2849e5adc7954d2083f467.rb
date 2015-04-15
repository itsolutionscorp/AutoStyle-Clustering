class PhoneNumber
  attr_reader :area_code, :number

  def initialize(phone_number)
    @raw_number = phone_number
    @number = verified_raw_number
    @area_code = @number[0..2]
  end
  
  def to_s
    "(%s) %s-%s" % [@area_code, @number[3,3], @number[6..-1]]
  end

  private

  def verified_raw_number
    return "0000000000" unless valid_number?

    @raw_number.length == 11 ? @raw_number[1..-1] : @raw_number
  end

  def valid_number?
    @raw_number = @raw_number.scan(/^\D?(\d{3})\D?\s?(\d{3})\D?(\d{4,5})$/).join
    @raw_number =~ /^1?\d{10}$/
  end
end
