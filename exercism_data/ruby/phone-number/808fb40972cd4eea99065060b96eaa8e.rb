class PhoneNumber

  def initialize(phone)
    @phone = phone
  end

  def number
    num = remove_leading_one(clean_phone_number)
    add_zeros_if_empty(num)
  end

  def clean_phone_number
    @phone.gsub(/\D/,"").scan(/^1?\d{10}$/).join
  end

  def add_zeros_if_empty(number)
    number == "" ? number = "0000000000" : number
  end

  def remove_leading_one(number)
    number.length == 11 ? number.gsub!(/^1/,"") : number
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..9]}"
  end

end
