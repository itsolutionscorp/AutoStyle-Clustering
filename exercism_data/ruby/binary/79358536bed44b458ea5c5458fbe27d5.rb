class Binary
  def initialize(string)
    @string = string
  end

  def to_decimal
    @decimal ||= convert_to_decimal
  end

  private

  def convert_to_decimal
    return 0 unless valid?
    sum = 0
    @string.chars.reverse.each_with_index do |digit, index|
      sum += digit.to_i * 2**index
    end
    sum
  end

  def valid?
    @valid ||= @string.match(/\A[01]+\z/)
  end
end
