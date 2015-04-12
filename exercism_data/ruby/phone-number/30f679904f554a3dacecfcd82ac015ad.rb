class Phone
  # http://en.wikipedia.org/wiki/Telephone_numbers_in_the_United_States

  DOMESTIC_NUMBER_LENGTH = 10
  FORMAT = /\A
    (?<country_code>1)?
    (?<domestic>
      (?<area_code>...)
      (?<central_office_code>...)
      (?<subscriber_number>....)
    )
  \z/x

  def initialize(number)
    @number = number
  end

  def number
    domestic_number || bad_number
  end

  def area_code
    group :area_code
  end

  def to_s
    pretty_number
  end

  private

  def bad_number
    "0" * DOMESTIC_NUMBER_LENGTH
  end

  def pretty_number
    "(#{area_code}) #{central_office_code}-#{subscriber_number}"
  end

  def central_office_code
    group :central_office_code
  end

  def subscriber_number
    group :subscriber_number
  end

  def domestic_number
    group :domestic
  end

  def group(name)
    digits[FORMAT, name]
  end

  def digits
    @number.gsub(/\D/, "")
  end
end