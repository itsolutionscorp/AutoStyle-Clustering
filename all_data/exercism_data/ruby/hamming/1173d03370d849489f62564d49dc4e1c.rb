class Hamming
  def self.compute(a, b)
    count = 0
    a = a.split("")
    b = b.split("")
    if a.length > b.length
        strandLen = b.length
    elsif a.length < b.length
        strandLen = a.length
    elsif a.length == b.length
        strandLen = a.length
    end
    strandLen.times{|x| count += 1 if a[x] != b[x]}
    count
  end
end
