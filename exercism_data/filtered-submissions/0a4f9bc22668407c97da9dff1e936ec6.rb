class Hamming
  def compute(s1, s2)
    ary = [s1.chars, s2.chars].sort_by { |s| s.count }
    ary_zipped = ary[0].zip(ary[1])
    ary_zipped.count { |e| e.first != e.last }
  end
end
