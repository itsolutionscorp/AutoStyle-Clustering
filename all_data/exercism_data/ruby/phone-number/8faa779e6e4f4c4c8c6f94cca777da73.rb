class PhoneNumber
  REGEXP = /\A(?<prefix>1)?(?<area_code>\d{3})(?<number>\d{7})\Z/

  attr_reader :area_code

  def initialize(string)
    @area_code, @number = '000', '0000000'
    
    return unless string.scan(/\d/).join =~ REGEXP
    
    @area_code, @number = $~[:area_code], $~[:number]
  end

  def number
    @area_code + @number
  end

  def to_s
    "(#{@area_code}) #{@number[0..5]}-#{number[6..-1]}"
  end

end
