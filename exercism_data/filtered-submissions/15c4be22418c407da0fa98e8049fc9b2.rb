class Hamming
  def compute(strand1, strand2)
    a = strand1.chars
    b = strand2.chars
    max = (a.count < b.count) ? a.count : b.count
    a = a[0,max]
    b = b[0,max]
    z = a.zip(b)
    z.inject(0) {|ret, e| ret += 1 if e.first != e.last; ret}
  end
end
