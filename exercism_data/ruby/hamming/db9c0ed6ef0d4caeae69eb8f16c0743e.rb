class Hamming
  def self.compute(original, compare)
    length = shortest_string_length(original, compare)
    convert_strings_to_arrays(original, compare)
    calculate_distance(original, compare, length)
  end

  private

  def self.shortest_string_length(original, compare)
    original.length < compare.length ? original.length : compare.length
  end

  def self.convert_strings_to_arrays(original, compare)
    original.split(//)
    compare.split(//)
  end

  def self.calculate_distance(original, compare, length)
    distance = 0
    for i in 0..length - 1
      distance = compare_each_element(original, compare, i, distance)
    end
    distance
  end

  def self.compare_each_element(original, compare, i, distance)
    if original[i] != compare[i]
      distance += 1
    end
    distance
  end
end
