class PhoneNumber
  REGEXP = /\A(?<prefix>1)?(?<number>(?<area_code>\d{3})\d{7})\Z/

  attr_reader :number, :area_code

  def initialize(string)
    
    @number = '0000000000'

    if string.scan(/\d/).join =~ REGEXP
      @number = $~[:number]
      @area_code = $~[:area_code]
    end
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..-1]}"
  end

end
