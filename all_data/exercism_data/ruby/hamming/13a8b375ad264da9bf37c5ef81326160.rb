class Hamming
  def self.compute(a, b)
    a = a[0...b.length].chars
    b = b[0...a.length].chars

    a.zip(b).delete_if {|x,y| x == y}.length
  end
end
