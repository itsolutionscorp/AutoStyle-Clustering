def compute(string_a, string_b)
    unless string_a.length == string_b.length
      raise ArgumentError.new("strings must be of equal length")
    end
    paired_chars = string_a.each_char.lazy.zip(string_b.each_char)
    return paired_chars.count { |char_a, char_b| char_a != char_b }
  end