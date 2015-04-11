class PhoneNumber
  attr_reader :number
  
  def initialize(dirty_number)
    @number = sanitize(dirty_number)
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{exchange}-#{station}"
  end

  private

  def sanitize(dirty_number)
    @number = get_digits(dirty_number)
    validate_number
  end

  def get_digits(dirty_number)
    dirty_number.delete("^0-9")
  end

  def validate_number
    if number.length == 10
      number
    elsif number.length == 11 && number.start_with?('1')
      remove_country_code
    else
      bad_number
    end
  end

  def bad_number
    '0' * 10
  end

  def remove_country_code
    number[1..-1]
  end

  def exchange
    number[3..5]
  end

  def station
    number[6..10]
  end
end
