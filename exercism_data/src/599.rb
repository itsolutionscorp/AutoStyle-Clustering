def compute(s1, s2)
    s1_ary = s1.each_char.to_a
    s2_ary = s2.each_char.to_a

    ary = [s1_ary, s2_ary].sort_by { |s| s.count }

    ary[0].zip(ary[1]).count { |e| e.first != e.last }
  end