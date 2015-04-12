class Hamming
  def compute(first_string, second_string)
    distance = 0

    first_string.length.times do |letter|
      distance += 1 if first_string[letter] != second_string[letter]
    end
    distance
  end
end
