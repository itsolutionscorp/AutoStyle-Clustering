module PhoneNumberFormatter
  US = '1'

  refine String do
    def sanitize!
      gsub! /[\s().,-]+/, ''
    end

    def correct_length?
      length == 10
    end

    def letters?
      /^\d+$/.match self
    end

    def country_code?
      length == 11 &&
        start_with?(US)
    end

    def error
      '0000000000'
    end
  end
end

class PhoneNumber
  using PhoneNumberFormatter

  attr_reader :number

  def initialize number
    @number = format number
  end

  def format number
    number.sanitize!

    if number.correct_length? &&
      number.letters?
      number
    elsif number.country_code?
      number[1..-1]
    else
      number.error
    end
  end

  def area_code
    number[0..2]
  end

  def first_part
    number[3..5]
  end

  def last_part
    number[6..-1]
  end

  def to_s
    "(#{area_code}) #{first_part}-#{last_part}"
  end
end
