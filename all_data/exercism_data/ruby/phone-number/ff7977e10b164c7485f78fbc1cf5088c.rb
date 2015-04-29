class PhoneNumber
  attr_reader :number_string

  def initialize(number_string)
    @number_string = number_string
  end

  def area_code
    number.slice(0, 3)
  end

  def to_s
    "(#{area_code}) #{head}-#{tail}"
  end

  def number
    @number ||= validated_number
  end

  private

  def head
    number.slice(3, 3)
  end

  def tail
    number.slice(6, 4)
  end

  def validated_number
    pattern_match || invalid_number
  end
  
  def pattern_match
    patterns.map {|sym| send sym }.detect {|x| x }
  end

  def patterns
    [
      :includes_letters,
      :ten_digit_number,
      :eleven_digit_number
    ]
  end

  def includes_letters
    invalid_number if number_string.match /[a-zA-Z]/
  end

  def ten_digit_number
    numbers_only if numbers_only.size == 10
  end

  def eleven_digit_number
    numbers_only.slice(1, 10) if numbers_only.size == 11 && numbers_only[0] == "1"
  end

  def numbers_only
    @numbers_only ||= number_string.gsub(/[^0-9]/,"")
  end

  def invalid_number
    "0000000000"
  end
end
