class Hamming
  def self.compute(arg1, arg2)
    array1 = arg1.split(//)
    array2 = arg2.split(//)

    if array1 == array2
      0
    else
      max = array1.count
      hamming_distance = 0
      max.times do |i|
        if array1[i] != array2[i]
          hamming_distance += 1
        end
      end
      hamming_distance
    end
  end
end
