class Array
  def sum()
    self.reduce &:+
  end
end

module Hamming
  module_function
  def compute( a, b )
    as_pairs( a, b ).map do |left, right|
      if left == right
        0
      else
        1
      end
    end.sum
  end

  def as_pairs( a, b )
    a.chars.zip b.chars
  end
end
