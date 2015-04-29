class PhoneNumber
  def initialize(number)
    @number = number
  end
  def number
    if trimmed.length == 10
      return trimmed
    elsif trimmed.length == 11 && trimmed[0] == "1"
      return trimmed[-10..-1]
    end
    "0000000000"
  end

  def trimmed
    return @number.tr(")(-.[A-Za-z]", "").gsub(/\s+/, "") unless @number.match(/[a-zA-z]/)
    "0000000000"
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..-1]}"
  end

end
