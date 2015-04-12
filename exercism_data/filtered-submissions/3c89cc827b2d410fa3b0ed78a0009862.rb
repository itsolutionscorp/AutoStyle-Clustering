def compute(string1, string2)
    hamming = 0
    [string1.length, string2.length].min.times do |l|
      if string1[l] != string2[l]
        hamming += 1
      end
    end
    hamming
  end