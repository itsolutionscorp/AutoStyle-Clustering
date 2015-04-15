class PhoneNumber
  FALSE = "0000000000"

  def initialize(input)
    @phone_n = input
  end

  def number
    return FALSE if @phone_n.match(/[a-z]/)
    @phone_n.tr!("^[0-9]", '')
    return @phone_n if @phone_n.size.eql?(10)
    if @phone_n.start_with?('1') and @phone_n.size.eql?(11)
      @phone_n.slice!(0)
      return @phone_n
    end
    FALSE
  end

  def area_code
    @phone_n[0..2]
  end

  def to_s
    @phone_n.slice!(0) if @phone_n.start_with?('1') and @phone_n.size.eql?(11)
    "(#{@phone_n[0..2]}) #{@phone_n[3..5]}-#{@phone_n[6..9]}"
  end
end
