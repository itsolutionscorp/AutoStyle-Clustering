class Trinary

  def initialize(sequence)
    @sequence = parse(sequence)
  end

  def to_decimal
    sequence_sum = []
    @sequence.each_with_index do |num, index|
      sequence_sum << (num * (3 ** index))
    end
    sequence_sum.reduce(:+)
  end

  def parse(sequence)
    new_seq = sequence.chars.reverse
    new_seq.map(&:to_i)
  end
end
