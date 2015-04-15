class Hamming

  def self.compute(first, second)
    hamming_distance = 0

    length = Hamming.return_shorter(first, second)

    length.times do |index|
      hamming_distance += 1 if first[index] != second[index]
    end
    hamming_distance
  end

  def self.return_shorter(first, second)
    return  first.length if first.length < second.length 
    second.length
  end

end
