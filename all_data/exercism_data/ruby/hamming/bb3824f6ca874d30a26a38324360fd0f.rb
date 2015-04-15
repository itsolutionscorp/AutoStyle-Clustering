class Array
  def sum()
    self.reduce &:+
  end
end

module Hamming
  module_function
  def compute( string_a, string_b )
    as_pairs( string_a, string_b ).map { |pair| difference *pair }.sum
  end

  def difference( char_a, char_b )
    if char_a == char_b
      0
    else
      1
    end
  end

  def as_pairs( string_a, string_b )
    string_a.chars.zip string_b.chars
  end
end
