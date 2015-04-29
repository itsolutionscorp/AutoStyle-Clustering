class NormalizedNumber
  attr_reader :number

  def initialize(number)
    @number = number
    remove_punctuation
    ensure_country_code
  end

  def short_number
    [area_code, exchange, suffix].join
  end

  def country_code
    parts[0]
  end

  def area_code
    parts[1]
  end

  def exchange
    parts[2]
  end

  def suffix
    parts[3]
  end

  def to_s
    "(#{area_code}) #{exchange}-#{suffix}"
  end

  private

  def remove_punctuation
    @number.gsub! /[^\d]+/, ''
  end

  def ensure_country_code
    @number = "0#{@number}" unless @number.length == 11
  end

  def parts
    @number.match(/(\d)(\d\d\d)(\d\d\d)(\d\d\d\d)/).to_a[1..-1]
  end
end

class Phone
  VALID_COUNTRY_CODES = %w(0 1)

  def initialize(raw_number)
    @raw_number = raw_number
  end

  def number
    if valid_number?
      normalized_number.short_number
    else
      invalid_number
    end
  end

  def to_s
    normalized_number.to_s
  end

  def area_code
    normalized_number.area_code
  end

  private

  def normalized_number
    @normalized_number ||= NormalizedNumber.new @raw_number
  end

  def valid_number?
    valid_length? && valid_country_code?
  end

  def valid_length?
    normalized_number.number.length == 11
  end

  def valid_country_code?
    VALID_COUNTRY_CODES.include? normalized_number.country_code
  end

  def invalid_number
    "0000000000"
  end
end
