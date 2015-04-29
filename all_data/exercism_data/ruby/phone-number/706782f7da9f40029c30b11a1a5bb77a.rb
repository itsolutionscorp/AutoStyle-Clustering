class PhoneNumber
  def initialize number
    @phone_number = trim number
  end

  def number
    if @phone_number.length == 10
      @phone_number
    elsif @phone_number.length == 11 && @phone_number.chr == "1"
      @phone_number.byteslice(1,10)
    else
      "0000000000"
    end
  end

  def area_code
    @phone_number.byteslice(0,3)
  end

  def to_s
    number.insert(0, "(").insert(4, ") ").insert(9,"-")
  end

  private
  def trim phone_number
    phone_number.tr("^[0-9]", "")
  end
end
