class PhoneNumber
  def initialize(phone_number)
    @phone_number = phone_number
  end

  def number
    test = @phone_number.scan(/\d+/).join.chars
    check_number(test)
  end

  def area_code
    number.slice(0,3)
  end

  def to_s
    result = number
    "(#{result[0,3]}) #{result[3,3]}-#{result[6,4]}"
  end

  private
  def check_number(phone_number)
    case
    when number_of_11digits?(phone_number)
      return normalize_number(phone_number).join
    when number_of_10digits?(phone_number)
      return phone_number.join
    else
      return "0000000000"
    end
  end

  def number_of_11digits?(phone_number)
    true if phone_number.length == 11 && phone_number[0].to_i == 1
  end

  def number_of_10digits?(phone_number)
    true if phone_number.length == 10
  end

  def normalize_number(phone_number)
      tmp = phone_number
      if number_of_11digits?(phone_number)
        tmp.shift
      end
      result = tmp
      return result
  end
end
