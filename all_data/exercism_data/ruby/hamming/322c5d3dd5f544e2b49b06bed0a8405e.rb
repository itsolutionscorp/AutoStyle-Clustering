class Hamming
  def self.compute(string_one,string_two)
    if string_one.length == string_two.length
      compare_arrays(string_one.split(//),string_two.split(//))
    else
      0
    end
  end
  def self.compare_arrays(array_one,array_two)
    hamming_distance = 0
    array_one.each_index do |index|
      if array_one[index] != array_two[index]
        hamming_distance = hamming_distance + 1
      end
    end
    hamming_distance
  end
end
