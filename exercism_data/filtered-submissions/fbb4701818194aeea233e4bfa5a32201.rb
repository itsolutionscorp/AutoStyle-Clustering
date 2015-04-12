def compute (string1, string2)
    [string1.length, string2.length].min.times.count do |i|
      string1[i] != string2[i] #this takes advantage of the .times method iterator to iterate i
    end
  end