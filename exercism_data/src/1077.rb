def compute(s1, s2)
    split_1 = s1.split('')
    split_2 = s2.split('')
    counter = 0
    hamming = 0

    s1.size.times do
      if split_1[counter] != split_2[counter]
        hamming += 1
      end
      counter += 1
    end
    hamming
  end