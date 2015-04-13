def compute(string1, string2)
    mismatches = 0
    min_length = [string1.length,string2.length].min
    min_length.times do |ind|
      mismatches = mismatches + 1 if string1[ind] != string2[ind]
    end
    mismatches
  end