class Hamming

  def self.compute(string_1, string_2)
    string_1.chars.map.with_index do |char, index|
      char == string_2[index] ? 0 : 1
    end.reduce(:+)
  end

end
