class PhoneNumber

  def initialize(numbers_string)
    @numbers_string = normalize(numbers_string)
  end

  def number
    valid_phone_number
  end


  def area_code
    valid_phone_number[0...3]
  end

  def to_s
    pretty_print(valid_phone_number)
  end

  private

  def valid_phone_number
    if @numbers_string.length == 11 && @numbers_string[0] == '1'
      @numbers_string[1..-1]
    elsif @numbers_string.length == 10
      @numbers_string
    else
      empty_phone_string
    end
  end

  def correct_length
    @numbers_string.length == (10 || 11)
  end

  def normalize(string)
    string.scan(/\d/).join
  end

  def pretty_print(phone_number)
    "(#{phone_number[0..2]}) #{phone_number[3..5]}-#{phone_number[6..-1]}"
  end

  def empty_phone_string
    '0000000000'
  end
end
