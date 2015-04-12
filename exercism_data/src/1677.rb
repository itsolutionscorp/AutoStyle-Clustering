class Hamming
  def compute(first, second)
    distance = 0
    index = 0
    first.each_char do |char|
      second_char = second[index]

      unless second_char == char
        distance += 1
      end
      index += 1
    end

    distance
  end
end
