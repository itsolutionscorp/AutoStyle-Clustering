class Phone
  # http://en.wikipedia.org/wiki/Telephone_numbers_in_the_United_States

  COUNTRY_CODE = "1"

  def initialize(number)
    @number = ExtendedString.new(number)
  end

  def number
    if normalized_number.length == Lengths.domestic
      normalized_number
    else
      bad_number
    end
  end

  def area_code
    number.first(Lengths.area_code)
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
    number.last(Lengths.subscriber_number)
  end

  def normalized_number
    if country_coded?
      digits.from(Lengths.country_code)
    else
      digits
    end
  end

  def country_coded?
    digits.length == Lengths.country_coded &&
      digits.first(Lengths.country_code) == COUNTRY_CODE
  end

  def digits
    @number.gsub(/\D/, "")
  end
end


class Phone
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
end


class ExtendedString < String
  def from(position)
    self[position..-1]
  end

  def first(count)
    self[0, count]
  end

  def last(count)
    self[-count..-1]
  end
end
