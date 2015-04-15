class PhoneNumber
  def initialize raw
    @raw = raw
    @number = number
  end

  def number
    invalid? ? '0000000000' :
        normalized.scan(/[0-9]/).map(&:to_i).join
  end

  def area_code
    @number[0..2]
  end

  def to_s
    "(#{area_code}) #{@number[3..5]}-#{@number[6..-1]}"
  end

  private
  def invalid?
    (@raw.match(/[a-zA-Z]/) || @raw.size == 9) ||
        (@raw.size == 11 && @raw.chars.first != "1") ||
        (@raw.size == 12 && @raw.chars.first == "1")
  end

  def normalized
    @raw.size == 11 && @raw.chars.first == "1" ? @raw[1..-1] : @raw
  end

end
