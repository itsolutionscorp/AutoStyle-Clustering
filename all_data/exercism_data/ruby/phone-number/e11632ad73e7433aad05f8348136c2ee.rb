class PhoneNumber
  def initialize(num)
    @num = num.match(/[a-zA-Z]/) ? "" : num.gsub(/\D/, "")
  end

  def number
    @num.length == 10 ? @num : @num.length == 11 && @num[0] == "1" ? @num[1..-1] : "0000000000"
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..9]}"
  end
end
