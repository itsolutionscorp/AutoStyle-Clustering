class Hamming
  def compute(s1, s2)
    s1_ary = s1.each_char.to_a
    s2_ary = s2.each_char.to_a

    ary = [s1_ary, s2_ary].sort { |a, b| a.count <=> b.count }

    ary_zipped = ary[0].zip ary[1]
    ary_zipped.select { |e| e.first != e.last }.count
  end
end
