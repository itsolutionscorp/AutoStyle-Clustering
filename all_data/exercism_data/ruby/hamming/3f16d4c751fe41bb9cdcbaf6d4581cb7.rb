class Hamming
  def self.compute(string, string_two)
    distance = 0
    shortest, longest = asc_sorted_strings(string, string_two)
    shortest.chars.each_with_index do |char, i|
      distance += 1 if char != longest[i]
    end
    distance
  end

  def self.asc_sorted_strings(string, string_two)
    if string.length > string_two.length
      [string_two, string]
    else
      [string, string_two]
    end
  end
end
