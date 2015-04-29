class Phone
  # http://en.wikipedia.org/wiki/Telephone_numbers_in_the_United_States

  COUNTRY_CODE = "1"

  module Lengths
    def self.country_code;        1; end
    def self.area_code;           3; end
    def self.central_office_code; 3; end
    def self.subscriber_number;   4; end

    def self.domestic
      area_code + central_office_code + subscriber_number
    end

    def self.country_coded
      country_code + domestic
    end
  end

  def initialize(number)
    @number = number
  end

  def number
    if normalized_number.length == Lengths.domestic
      normalized_number
    else
      bad_number
    end
  end

  def area_code
    number[0, Lengths.area_code]
  end

  def to_s
    pretty_number
  end

  private

  def bad_number
    "0" * Lengths.domestic
  end

  def pretty_number
    "(#{area_code}) #{central_office_code}-#{subscriber_number}"
  end

  def central_office_code
    number[Lengths.area_code, Lengths.central_office_code]
  end

  def subscriber_number
    number[-Lengths.subscriber_number..-1]
  end

  def normalized_number
    if country_coded?
      digits[Lengths.country_code..-1]
    else
      digits
    end
  end

  def country_coded?
    digits.length == Lengths.country_coded &&
      digits[0, Lengths.country_code] == COUNTRY_CODE
  end

  def digits
    @number.gsub(/\D/, "")
  end
end
