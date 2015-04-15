class Phone
  attr_reader :phone_number

  def initialize(phone_number)
    @phone_number = phone_number
  end

  def number
    return strip(clean) if strip(clean).length == 10
    "0000000000"
  end

  def to_s
    "(#{area_code}) #{prefix}-#{postfix}"
  end

  def area_code
    number[0..2]
  end

  def prefix
    number[3..5]
  end

  def postfix
    number[6..10]
  end

  private

  def clean
    phone_number.to_s.chars.select do |character|
      is_number?(character)
    end.join
  end

  def strip(phone_number)
    return phone_number[1..10] if phone_number[0..1] == "11"
    phone_number
  end

  def is_number?(character)
    Float(character) != nil rescue false
  end
end
