def compute(string1,string2)
    num_of_false = 0
    string1.length.times do |n|
      num_of_false += 1 if string1[n] != string2[n]
    end
    return num_of_false
  end