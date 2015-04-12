class Hamming
  def compute(string_one, string_two)
    hamming_distance = 0

    string_one.chars.zip(string_two.chars).each do |duo|
      if duo[0] && duo[1] && duo[0] != duo[1]
        hamming_distance += 1
      end
    end

    hamming_distance
  end
end
