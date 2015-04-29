class Hamming

  def self.compute(a,b)
    a[0..b.length-1].chars.zip(b.chars).map {|arr| (arr[0] == arr[1] ? 0 : 1)}.reduce (:+)
  end
end
