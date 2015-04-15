module Hamming
  module_function
  def compute( string_a, string_b )
    as_pairs( string_a, string_b ).count do |char_a, char_b|
      char_a != char_b
    end
  end

  def as_pairs( string_a, string_b )
    string_a.chars.zip string_b.chars
  end
end
