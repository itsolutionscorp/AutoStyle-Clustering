class PhoneNumber
  attr_reader :area_code, :exchange, :subscriber_number, :value

  def initialize(value)
    @value = value
    @area_code, @exchange, @subscriber_number = number.scan(/(...)(...)(....)/).flatten
  end

  def to_s
    "(#{area_code}) #{exchange}-#{subscriber_number}"
  end

  def number
    parsed.to_s
  end

  def parsed
    @parsed ||= PhoneNumberParser.new(value).only_numerals.without_trunk_code.of_valid_length
  end

end

class PhoneNumberParser
  attr_reader :number

  def initialize(value)
    @number = value
  end

  def only_numerals
    PhoneNumberParser.new(number.gsub(/[^\d]/,''))
  end

  def without_trunk_code
    has_trunk_code? ? PhoneNumberParser.new(number[1..-1]) : self 
  end

  def of_valid_length
    (number.length == 10 || has_trunk_code?) ? self : PhoneNumberParser.new("0000000000")
  end

  def has_trunk_code?
    number.start_with?('1') && number.length == 11
  end

  def to_s
    @number
  end
end
