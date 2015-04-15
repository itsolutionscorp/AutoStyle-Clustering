class Hamming
  def self.compute(str1, str2)
    str1_arr = str1.split('')
    str2_arr = str2.split('')

    str1_arr.each_with_index.count { |n,i| n != str2_arr[i] }
  end
end
