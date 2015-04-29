class Binary
  attr_reader :string
  
  def initialize(string)
    @string = string
  end

  def to_decimal
    return 0 unless valid_binary?

    digits_with_powers.map { |digit, power|
      digit.to_i * (2 ** power)
    }.reduce(:+)
  end

  private

  def digits_with_powers
    string.chars.reverse.each_with_index
  end

  def valid_binary?
    string =~ /\A[01]+\z/
  end
end
