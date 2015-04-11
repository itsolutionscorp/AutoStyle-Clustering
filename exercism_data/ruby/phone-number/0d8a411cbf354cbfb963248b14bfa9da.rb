class PhoneNumber
  def initialize(input)
    @number = convert_to_number(input)
  end
  def number
    @number
  end
  def area_code
    @number.slice(0..2)
  end
  private
  def convert_to_number(input)
    if has_letters?(input)
      return "0" * 10
    else
      raw_number = input.chars.select { |c| c =~ /[0-9]+/ }.join
      p raw_number
      if !ten_letters?(raw_number) && !eleven_letters?(raw_number)
        return "0" * 10
      else
        return raw_number
      end
    end

  end
  def filter_numerics(chars)
    chars.select { |c| c =~ /[0-9]+/ }
  end
  def has_letters?(string)
    if string.chars.select { |c| c =~ /[A-Za-z]+/}.join == ""
      false
    else
      true
    end
  end
  def ten_letters?(input)
    input.length == 10
  end
  def eleven_letters?(input)
    input.length == 11 && input[0] == "1"

  end
end
