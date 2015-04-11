class PhoneNumber

  attr_reader :phone_number

  def initialize(number)
    @phone_number = number
  end

  def number
    if cleaned_number.length == 10
      cleaned_number
    elsif cleaned_number.length == 11 && phone_number[0] == "1"
      cleaned_number[1..-1]
    else
      "0000000000"
    end
  end

  def cleaned_number
    phone_number.scan(/\d+/).join
  end

  def area_code
    number[0...3]
  end

  def to_s
    "(#{area_code}) #{number[3..-1].insert(3, '-')}"
  end


end
