class PhoneNumber
  module Normalizers
    extend self

    INVALID_NUMBER = '0000000000'.freeze
    US_PHONE_FORMAT = /^1\d{10}$/.freeze

    def scrub_non_digits(number)
      number.gsub(/\D/, '')
    end

    def drop_us_code(number)
      if number.match(US_PHONE_FORMAT)
        number.slice(1..-1)
      else
        number
      end
    end

    def filter_invalid_number(number)
      number.size == 10 ? number : INVALID_NUMBER
    end
  end

  attr :number

  def initialize(number)
    @number = normalize(number)
  end

  def area_code
    number[0, 3]
  end

  def prefix
    number[3, 3]
  end

  def line_number
    number[6, 4]
  end

  def to_s
    "(#{area_code}) #{prefix}-#{line_number}"
  end

  private

  def normalize(number)
    number = Normalizers.scrub_non_digits(number)
    number = Normalizers.drop_us_code(number)
    number = Normalizers.filter_invalid_number(number)

    number
  end
end
