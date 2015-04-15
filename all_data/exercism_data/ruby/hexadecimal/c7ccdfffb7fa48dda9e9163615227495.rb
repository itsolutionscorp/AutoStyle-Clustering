class Hexadecimal
  attr_reader :num

  def initialize num
    @num = num
  end

  def to_decimal
    invalid_hex ? 0 : num.to_i(16)
  end

  private

  def invalid_hex
    !!num.gsub(/[0-9]/, '').match(/[^abcdef]/)
  end
end
