class PhoneNumber
  attr_reader :number

  def initialize(number)
    @number = validate_length(clean(validate_no_letters(number)))
  end

  def area_code
    @number.slice(0, 3)
  end

  def to_s
    "(#{area_code}) #{@number.slice(3, 3)}-#{@number.slice(6, 4)}"
  end

  private

  def clean(number)
    number.strip.gsub(/[\(\)\-\. ]/, "")
  end

  def validate_length(number)
    return number.slice(1, 10) if number.length == 11 && number[0] == "1"
    return "0000000000" if number.length != 10
    number
  end

  def validate_no_letters(number)
    return "0000000000" if has_letters?(number)
    number
  end

  def has_letters?(number)
    number.upcase != number || number.downcase != number
  end
end
