def compute str1, str2 
    count = 0
    str1 = str1.to_s.split('')
    str2 = str2.to_s.split('')
    str1.length.times do |i|
        count += 1 if str1[i] != str2[i]
    end
    count
  end