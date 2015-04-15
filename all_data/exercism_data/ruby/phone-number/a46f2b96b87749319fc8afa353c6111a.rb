class Phone
  BAD_NUMBER = "0" * 10
  AREA_CODE_LENGTH = 3

  def initialize(number)
    @number = number
  end

  def number
    if normalized_number.length == 10
      normalized_number
    else
      BAD_NUMBER
    end
  end

  def area_code
    number[0, AREA_CODE_LENGTH]
  end

  def to_s
    pretty_number
  end

  private

  def pretty_number
    "(#{area_code}) #{pretty_local_number}"
  end

  def pretty_local_number
    format("%s-%s", local_number[0,3], local_number[3..-1])
  end

  def local_number
    number[AREA_CODE_LENGTH..-1]
  end

  def normalized_number
    if digits.length == 11 && digits[0] == "1"
      digits[1..-1]
    else
      digits
    end
  end

  def digits
    @number.gsub(/\D/, "")
  end
end
