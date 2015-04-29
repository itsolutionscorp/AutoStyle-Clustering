require 'active_support/all'

class PhoneNumber
  INVALID_NUMBER = "0" * 10
  VALID_LENGTH = 10

  def initialize(number)
    @number = number
  end

  def area_code
    number.first(3)
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
    number = remove_leading_one_from_large_number(number)
    valid_number(number)
  end

  def human_format
    "(%s) %s-%s" % parts
  end

  def remove_non_digits(number)
    number.gsub(/\D/, '')
  end

  def remove_leading_one_from_large_number(number)
    if trimmable?(number)
      number.last(VALID_LENGTH)
    else
      number
    end
  end

  def parts
    number.scan(/(\d{3})(\d{3})(\d{4})/).flatten
  end

  def trimmable?(number)
    number.length == VALID_LENGTH + 1 && number.start_with?("1")
  end

  def valid_number(number)
    if number.length == VALID_LENGTH
      number
    else
      INVALID_NUMBER
    end
  end
end
