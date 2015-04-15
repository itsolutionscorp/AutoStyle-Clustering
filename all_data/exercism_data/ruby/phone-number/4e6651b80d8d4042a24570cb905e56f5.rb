class PhoneNumber
  def initialize phone_number_string
    @raw_phone = phone_number_string
  end

  def number
    @clean_number = case numbers_only
                    when no_country_code then numbers_only
                    when good_country_code then phone_with_country_code
                    else invalid_number
                    end
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..9]}"
  end

  private

  def phone_with_country_code
    numbers_only[1..-1]
  end

  def no_country_code
    ->(raw_number){ raw_number.length == 10 }
  end

  def good_country_code
    ->(raw_number){ raw_number.length == 11 && raw_number.start_with?('1') }
  end

  def numbers_only
    @numbers_only = raw_phone.gsub(/\D/, '')
  end

  def raw_phone
    @raw_phone
  end

  def invalid_number
    '0000000000'
  end
end
