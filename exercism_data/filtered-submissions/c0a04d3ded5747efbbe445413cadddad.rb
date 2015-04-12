def compute(str1,str2)
    count = 0
    [str1.length, str2.length].min.times do |i|
      count +=1 if ( str1[i] != str2[i] )
    end
    count
  end