class PhoneNumber
  attr_reader :number
  
  def initialize(number)
    @number = number
    sanitize_number!
  end

  def area_code
    @area_code ||= number[0..2]
  end

  def to_s
    @to_s ||= "(#{area_code}) #{exchange}-#{station}"
  end

  private

  def sanitize_number!
    get_digits!
    fix_number!
  end

  def get_digits!
    # @number = number.delete("^0-9")
    # @number = number.gsub(/\D/, '')
    @number = number.split(/\D/).join
  end

  def fix_number!
    @number = if number.length == 11 && number.start_with?('1')
      remove_1
    elsif number.length == 10
      number
    else
      bad_number
    end
  end

  def bad_number
    '0' * 10
  end

  def remove_1
    @number[1..-1]
  end

  def exchange
    number[3..5]
  end

  def station
    number[6..10]
  end
end
