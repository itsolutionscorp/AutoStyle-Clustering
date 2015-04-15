class Trinary

  def initialize(sequence)
    @sequence = parse(sequence)
  end

  def to_decimal
    @sequence.map.with_index do |num, index|
      (num * (3 ** index))
    end.reduce(:+)
  end

  def parse(sequence)
    new_sequence = sequence.chars.reverse
    new_sequence.map(&:to_i)
  end
end
