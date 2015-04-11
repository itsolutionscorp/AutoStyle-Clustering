class Hamming
  def self.compute(string1, string2)
    array1 = string1.chars
    array2 = string2.chars
    count = 0
    array1.each_with_index do |val, index|
      if array2[index] != val
        count += 1
      end
    end
    count
  end
end
