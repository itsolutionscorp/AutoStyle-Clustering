def compute s1,s2
    (0...[s1.length, s2.length].min).reduce(0) {|memo, i|
      memo += 1 if s1[i] != s2[i]
      memo
    }
  end