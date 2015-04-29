def compute str1,str2
    min_length = [str1.length,str2.length].min
    count = 0
    min_length.times do |n|
      count+=1 unless str1[n] == str2[n]
    end
    count
  end