class PhoneNumber
  def initialize(phone_number)
    if phone_number.gsub(/[^A-z]/, "") != ""
      @phone_number = "0000000000"
    elsif phone_number.length == 11 && phone_number[0] == "1"
      @phone_number = phone_number[1..10]
    else
      @phone_number = phone_number.gsub(/[^0-9]/, "")
    end
  end

  def number
    if @phone_number.length < 10 || @phone_number.length > 10 && @phone_number[0] != "1" || @phone_number.length > 11
      "0000000000"
    else
      @number = @phone_number
    end
  end

  def area_code
    @phone_number[0..2]
  end

  def to_s
    "(" + @phone_number[0..2] + ") " + @phone_number[3..5] + "-" + @phone_number[6..9]
  end
end
