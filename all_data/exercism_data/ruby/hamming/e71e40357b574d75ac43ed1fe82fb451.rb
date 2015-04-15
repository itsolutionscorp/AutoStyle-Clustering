class Hamming
  def self.compute(a,b)
    count = 0
    for i in 0..(a.size > b.size ? b.size : a.size)-1
      count+=1 unless a[i] == b[i]
    end
    count
  end
end
