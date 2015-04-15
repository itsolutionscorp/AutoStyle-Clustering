class PhoneNumber
  attr_reader :number
  def initialize(number)
    @number = (number =~ /^[023456789]1|^\d{0,9}$/) ? '0000000000' : number.gsub(/^11/,'1').gsub(/[^0-9]/, '')
  end
  def area_code
    @number[0..2]
  end
  def to_s
    @number.gsub(/(\d{3})(\d{3})(\d+)/,"(\\1) \\2-\\3")
  end
end
