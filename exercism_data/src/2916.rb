class Hamming
  
  def compute str1, str2
    return 0 if str1 == str2

    str1_arr = str1.split('')
    str2_arr = str2.split('')
    count = 0
    limit = (str2_arr.size < str1_arr.size) ? str2_arr.size : str1_arr.size
    limit.times do |i|
      count+=1 if str2_arr[i] != str1_arr[i]
    end
    
    return count
  end
  
end
