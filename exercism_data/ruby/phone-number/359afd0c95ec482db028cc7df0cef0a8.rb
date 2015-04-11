class Phone
  def initialize(phone_number)
    @phone_number = phone_number
  end

  def number
    normalized_number
  end

  def area_code
    area_part
  end

  def to_s
    "(#{area_part}) #{local_part[0..2]}-#{local_part[3..-1]}"
  end

  private

  def area_part
    normalized_number[0..2]
  end

  def local_part
    normalized_number[3..-1]
  end

  def normalized_number
    if international_format?
      cleaned_number[1..-1]
    elsif national_format?
      cleaned_number
    else
      null_number
    end
  end

  def international_format?
    cleaned_number_length == 11 && cleaned_number[0] == '1'
  end

  def national_format?
    cleaned_number_length == 10
  end

  def cleaned_number_length
    cleaned_number.length
  end

  def cleaned_number
    phone_number.gsub(/[^0-9]/, '')
  end

  attr_reader :phone_number

  def null_number
    "0" * 10
  end

end
