class PhoneNumber
  def initialize(phone_number)
    @phone_number = phone_number
  end

  def number
    cleaned_number = phone_number.gsub(/\W/, '')

    if cleaned_number.length == 11 && cleaned_number[0] == '1'
      cleaned_number = cleaned_number[1..-1]
    end

    if cleaned_number =~ /\D/ || cleaned_number.length != 10
      cleaned_number = '0' * 10
    end

    cleaned_number
  end

  def area_code
    number[0..2]
  end

  def to_s
    first_three = number[3..5]
    last_four = number[6..9]

    "(#{area_code}) #{first_three}-#{last_four}"
  end

  private

  attr_reader :phone_number
end
