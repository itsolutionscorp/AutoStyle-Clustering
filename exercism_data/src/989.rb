class Hamming
  def compute(first_string, second_string)
    length = [first_string, second_string].min.length
    differences = 0
    (0...length).each do |i|
      differences += 1 if first_string[i] != second_string[i]
    end
    return differences
  end
end
