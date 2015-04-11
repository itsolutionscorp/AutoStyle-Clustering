class PhoneNumber
  INVALID_NUMBER = "0" * 10
  US_COUNTRY_CODE = "1"
  VALID_LENGTH = 10

  def initialize(number)
    @number = number
  end

  def area_code
    number[0,3]
  end

  def number
    clean(@number)
  end

  def to_s
    human_format
  end

  private

  def clean(number)
    number = remove_non_digits(number)
    number = remove_country_code(number)
    valid_number(number) || INVALID_NUMBER
  end

  def human_format
    "(%s) %s-%s" % parts
  end

  def remove_non_digits(number)
    number.gsub(/\D/, "")
  end

  def remove_country_code(number)
    if has_country_code?(number)
      number[US_COUNTRY_CODE.length, VALID_LENGTH]
    else
      number
    end
  end

  def parts
    number.scan(/(\d{3})(\d{3})(\d{4})/).flatten
  end

  def has_country_code?(number)
    number.length == VALID_LENGTH + US_COUNTRY_CODE.length && number.start_with?(US_COUNTRY_CODE)
  end

  def valid_number(number)
    if number.length == VALID_LENGTH
      number
    else
      nil
    end
  end
end
