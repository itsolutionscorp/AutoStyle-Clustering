class Phone
  INVALID_NUMBER = '0000000000'
  attr_reader :number

  def initialize(number)
    @number = number.gsub(/\D/, '')
    @number = @number[1..-1] if eleven_digits_starting_with_one?
    @number = INVALID_NUMBER if invalid_number?
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..10]}"
  end

  private

  def eleven_digits_starting_with_one?
    number.size == 11 && number[0] == '1'
  end

  def invalid_number?
    number.size != 10
  end
end
