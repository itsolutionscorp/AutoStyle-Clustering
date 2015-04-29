class PhoneNumber

  attr_reader :number

  def initialize(num_string)
    @number = clean num_string
  end

  def clean(num_string)
    num = remove_non_numeric_characters(num_string)
    num = replace_invalid_numbers(num)
    num = remove_leading_one(num)
    num = replace_numbers_with_invalid_country_code(num)
  end

  def area_code
    number[0,3]
  end

  def to_s
    "(#{number[0,3]}) #{number[3,3]}-#{number[6,4]}"
  end

  def remove_non_numeric_characters(num)
    num.tr('^0-9', '')
  end

  def replace_invalid_numbers(num)
    num.length > 11 || num.length < 10 ? '0000000000' : num
  end

  def remove_leading_one(num)
    num.length == 11 && num[0] == '1' ? num[1, 10] : num
  end

  def replace_numbers_with_invalid_country_code(num)
    num.length == 11 && num[0] != '1' ? '0000000000' : num
  end
end
