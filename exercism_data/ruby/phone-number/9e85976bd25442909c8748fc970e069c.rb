class PhoneNumber

  attr_reader :phone_number

  def initialize(phone_number)
    @phone_number = phone_number.to_s
  end

  def number
    only_numbers
    without_leading_one
    phone_number
  end

  private
  def only_numbers
    phone_number.gsub!(/[^\d]/, '')
  end

  def without_leading_one
    phone_number.gsub!(/1(?<number>\d{10})/, '\0')
  end

end
