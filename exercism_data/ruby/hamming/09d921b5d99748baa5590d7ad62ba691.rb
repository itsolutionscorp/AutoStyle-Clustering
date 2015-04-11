# exercism ruby/hamming
module Hamming
  module_function

  def compute(*strands)
    @strands = strands
    length_match? || fail('different strand lengths not supported')
    strands[0].select_by_char(&mismatches).count
  end

  def length_match?
    @strands[0].length == @strands[1].length
  end

  def mismatches
    proc { |char, index| char != @strands[1][index] }
  end
end

class String
  def select_by_char(&block)
    each_char.with_index.select(&block)
  end
end
