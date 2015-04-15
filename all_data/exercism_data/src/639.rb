def compute(s1,s2)
    ham = 0
    if s1.length < s2.length
      for i in 0..(s1.length-1)
        if s1[i] != s2[i]
          ham += 1
        end
      end
      return ham
    else
      for i in 0..(s2.length-1)
        if s1[i] != s2[i]
          ham += 1
        end
      end
      return ham
    end
  end