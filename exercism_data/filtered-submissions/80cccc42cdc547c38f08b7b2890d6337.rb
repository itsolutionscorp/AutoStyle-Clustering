class Hamming
  def compute(a, b)
    hamming_distance = 0
    counter = 0

    a.each_char do |s|
      hamming_distance += 1 unless s == b[counter]
      counter += 1
      break if b.length == counter
    end
    return hamming_distance
  end
end
