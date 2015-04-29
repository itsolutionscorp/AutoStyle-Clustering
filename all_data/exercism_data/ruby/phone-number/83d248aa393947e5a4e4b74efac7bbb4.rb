class PhoneNumber

  def initialize(phone_string)
    phone_string = normalize(phone_string)
    @country_code, @phone_number = spit_phone_number(phone_string)
  end

  def number
    return '0000000000' unless valid?
    @phone_number
  end

  def area_code
    @phone_number[0,3]
  end

  def to_s
    "(#{area_code}) #{@phone_number[3,3]}-#{@phone_number[6,4]}"
  end

  private

  def valid?
    @phone_number.length == 10
  end

  def normalize(phone_string)
    phone_string.gsub(/[^0-9]/, '')
  end

  def spit_phone_number(number)
    if number.length == 11 && number[0] == '1'
      [number[0], number[1,number.length]]
    else
      [:no_country_code,number]
    end
  end

end
