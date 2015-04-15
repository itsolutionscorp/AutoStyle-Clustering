class PhoneNumber

  attr_reader :phone_number

  def initialize(number)
    @phone_number = number
  end

  def number
    selected_number
  end

  def area_code
    number[0...3]
  end

  def to_s
    "(#{area_code}) #{number[3..-1].insert(3, '-')}"
  end

  private

  def cleaned_number
    phone_number.scan(/\d+/).join
  end

  def valid?
    valid_number
  end

  def valid_number
    valid_numbers.select do |k,v|
      cleaned_number =~ k
    end.values.first
  end

  def valid_numbers
    {
      /^\d{10}$/ => cleaned_number,
      /^1\d{10}$/ => cleaned_number[1..-1]
    }
  end

  def selected_number
    if valid?
      valid_number
    else
      "0000000000"
    end
  end

end
