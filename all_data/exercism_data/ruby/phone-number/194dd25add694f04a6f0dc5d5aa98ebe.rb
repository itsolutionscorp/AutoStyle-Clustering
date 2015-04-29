class PhoneNumber
  INVALID_NUMBER = "0000000000"
  FORMAT = "(%{area_code}) %{group1}-%{group2}"

  def initialize(input)
    @number = clean_number(input)
  end

  attr_reader :number

  def area_code
    number[0, 3]
  end

  def group1
    number[3, 3]
  end

  def group2
    number[6, 4]
  end

  def to_s
    FORMAT % {
      area_code: area_code,
      group1: group1,
      group2: group2
    }
  end

  private

  def clean_number(input)
    if input =~ /[[:alpha:]]/
      INVALID_NUMBER
    else
      digits = input.gsub(/[^0-9]/, '')
      clean_digits(digits)
    end
  end

  def clean_digits(digits)
    case digits.length
    when 10
      digits
    when 11
      clean_11_digits(digits)
    else
      INVALID_NUMBER
    end
  end

  def clean_11_digits(digits)
    if digits.start_with? '1'
      digits[1, 10]
    else
      INVALID_NUMBER
    end
  end
end
