class Hamming
  #str == strand versus the common abbreviation of string
  def compute(first_str, second_str)
    min_length = [first_str.length, second_str.length].min

    hamms = 0
    min_length.times do |i|
      hamms += 1 if first_str[i] != second_str[i]
    end
    hamms
  end
end
#hamms may or may not be a beer reference...
