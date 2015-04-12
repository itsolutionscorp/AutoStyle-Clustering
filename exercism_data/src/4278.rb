class Hamming
  def compute(string_a, string_b)
    if string_a.size > string_b.size
      string_a, string_b = string_b, string_a
    end
    mapped = string_a.chars.map.with_index do |e, i|
      e == string_b[i] ? 0 : 1
    end
    # require 'pry'; binding.pry
    mapped.reduce(0, &:+)
  end
end
