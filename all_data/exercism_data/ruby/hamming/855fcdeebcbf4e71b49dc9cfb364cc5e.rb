# exercism ruby/hamming
module Hamming
  module_function

  def compute(*strands)
    @strands = strands
    @length = strands[0].length

    length_match? || fail('different strand lengths not supported')
    distance = 0

    (0..@length).each do |index|
      distance += 1 if mismatch_at?(index)
    end

    distance
  end

  def length_match?
    @strands[0].length == @strands[1].length
  end

  def mismatch_at?(index)
    @strands[0][index] != @strands[1][index]
  end
end
