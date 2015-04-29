class PhoneNumber

  attr_reader :phone_number

  def initialize phone_number
    @phone_number = squeeze!(clean! phone_number)
  end

  def number
    @phone_number.length == 10 ? @phone_number : '0000000000'
  end

  def clean! phone_number
    phone_number.gsub(/[^0-9]/,'')
  end

  def squeeze! number
    number.length == 11 && number.start_with?('1') ? number.slice(1,number.length-1) : number
  end

  def area_code 
    @phone_number.slice(0,3)
  end

  def exchange
    @phone_number.slice(3,3) 
  end

  def subscriber
    @phone_number.slice(6,4)
  end

  def to_s
    "(#{area_code}) #{exchange}-#{subscriber}"
  end

end
