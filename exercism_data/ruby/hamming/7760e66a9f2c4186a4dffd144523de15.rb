module Hamming
  module_function

  def compute(a, b)
    a.chars.zip(b.chars).select { |x, y|
      x && y && x != y
    }.size
  end
end