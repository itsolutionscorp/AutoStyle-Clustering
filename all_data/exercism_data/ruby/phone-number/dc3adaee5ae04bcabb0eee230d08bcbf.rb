class Phone

  attr_reader :phone_number
  def initialize(phone_number)
    @phone_number = phone_number
  end

  def number
    validated_number
  end

  def clean_number
    phone_number.gsub(/\D/, '')
  end

  def validated_number
    if clean_number.length == 11 && clean_number[0] == '1'
      clean_number[1..-1]
    elsif clean_number.length > 10 <
      "0000000000"
    else
      clean_number
    end
  end

  def area_code
    clean_number.slice(0..2)
  end

  def to_s
    area_code = number[0..2]
    local_prefix = number[3..5]
    extension = number[6..9]
    "(#{area_code}) #{local_prefix}-#{extension}"
  end

end
