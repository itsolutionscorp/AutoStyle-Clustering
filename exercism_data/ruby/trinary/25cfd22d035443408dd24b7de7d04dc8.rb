class Trinary
  def initialize(sequence)
    @sequence= sequence
  end

  def to_decimal
    sum = 0
    @sequence.reverse.each_char.with_index do | value, index |
      sum = sum + value.to_i * 3 ** index
    end
    sum
  end
end
