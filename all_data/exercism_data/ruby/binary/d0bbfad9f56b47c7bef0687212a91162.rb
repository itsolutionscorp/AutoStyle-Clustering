class Binary
  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    total = 0
    return total if invalid?
    binary.reverse.each_char.with_index do |c, i|
      total += (2 ** i) * c.to_i
    end
    total
  end

  private

  attr_reader :binary

  def invalid?
    binary.match(/\D+/)
  end
end
