class Binary
  def initialize(num)
    @num = String(num)
  end

  def to_decimal
    total = 0

    pattern = /[^01]/ # anything other than 0 or 1

    unless num.match(pattern)
      num.chars.reverse_each.with_index do |digit, index|
        total += digit.to_i * 2**index
      end
    end

    total
  end

  private

  attr_reader :num
end
