class PhoneNumber

  def initialize(raw_number)
    @phone_number = raw_number
  end

  def number
    prepare_number(@phone_number)
  end

  def area_code
    number[0,3]
  end

  def prefix
    number[3,3]
  end

  def suffix
    number[6,4]
  end

  def to_s
    prepare_number(@phone_number)
    "(%s) %s-%s" % [area_code, prefix, suffix]
  end

  private

  def prepare_number(phone_number)
    phone_number = clean(phone_number)
    phone_number = remove_leading_one(phone_number)
    phone_number = set_to_zeros(phone_number) unless valid?(phone_number)
    phone_number
  end

  def clean(phone_number)
    phone_number = phone_number.scan(/[0-9]/).join 
  end

  def set_to_zeros(phone_number)
    phone_number = "0000000000"
  end

  def remove_leading_one(phone_number)
    if phone_number.length == 11 && phone_number.start_with?("1")
      phone_number = phone_number[1..-1]
    else
      phone_number
    end
  end

  def valid?(phone_number)
    phone_number.length == 10
  end

end
