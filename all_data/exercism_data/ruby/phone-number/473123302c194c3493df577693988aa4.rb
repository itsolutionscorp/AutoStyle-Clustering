class PhoneNumber
  def initialize number
    @phone_number = trim number
  end

  def number
    if @phone_number.length == 10
      @phone_number
    elsif country_code? @phone_number
      @phone_number.byteslice(1,10)
    else
      "0000000000"
    end
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..10]}"
  end

  private
  def trim phone_number
    phone_number.tr("^[0-9]", "")
  end

  def country_code? phone_number
    phone_number.length == 11 and phone_number.start_with? '1'
  end
end
