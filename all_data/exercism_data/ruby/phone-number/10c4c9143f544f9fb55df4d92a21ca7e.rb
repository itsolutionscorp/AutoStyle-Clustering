class PhoneNumber

  def initialize(phone_number)
    @phone_number = phone_number.gsub(/[ ()-.]/,"")
    @phone_number = @phone_number[1..-1] if @phone_number.length == 11 && @phone_number[0] == "1"
  end

  def area_code
    @phone_number[0..2]
  end

  def number
    valid = @phone_number.size == 10 && @phone_number.to_i.to_s == @phone_number

    valid ? @phone_number : "0000000000"
  end

  def to_s
    "(#{area_code}) #{@phone_number[3..5]}-#{@phone_number[6..9]}"
  end

end
