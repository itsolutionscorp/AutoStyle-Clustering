class Hamming
  
  def self.compute(first, second)
    raise "Unequal DNA length!" unless first.length == second.length
    first_array = first.split('')
    second_array = second.split('')
    first_hash = self.make_hash_from_array(first_array)
    self.count_differences(first_hash, second_array)
  end

  def self.make_hash_from_array(array)
    result = {}
    array.each_with_index do |char, index|
      result[index] = char
    end
    return result
  end

  def self.count_differences(first_hash, second_array)
    hamming = 0
    second_array.each_with_index do |char, index|
      hamming += 1 unless first_hash[index] == char
    end
    return hamming
  end
  
end
