#!/usr/bin/ruby

class Hamming
  
  def compute(str1, str2)
    string_count = 0
    iterator = 0
    if str1.size == str2.size
      while iterator < str1.size  do
        if str1[iterator] != str2[iterator]
          string_count += 1
        end
        iterator +=1
      end
    end
    return string_count
  end
end
