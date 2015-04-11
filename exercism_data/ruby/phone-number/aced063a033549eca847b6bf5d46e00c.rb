class PhoneNumber

  def initialize(phone)
    @phone = phone
  end

  def number
    num = @phone.gsub(/\D/,"").scan(/^1?\d{10}$/).join
    num = num.gsub(/^1/,"") if num.length == 11
    num = "0000000000" if num == ""
    num
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{number[0..2]}) #{number[3..5]}-#{number[6..9]}"
  end

end
