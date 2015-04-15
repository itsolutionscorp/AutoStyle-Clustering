class PhoneNumber
  DEFAULT_PHONE_NUMBER = %w(000 000 0000)
  VALID_PHONE_NUMBER   = /^1?(\d{3})(\d{3})(\d{4})$/

  attr_accessor :area_code, :sub_first, :sub_last


  def initialize(number)
    digits = number.gsub(/\W/, '').scan(VALID_PHONE_NUMBER)[0]
    @area_code, @sub_first, @sub_last = digits || DEFAULT_PHONE_NUMBER
  end

  def number
    "#{@area_code}#{@sub_first}#{@sub_last}"
  end

  def to_s
    "(#{@area_code}) #{@sub_first}-#{@sub_last}"
  end
end
