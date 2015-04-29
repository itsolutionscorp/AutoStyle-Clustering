class Hamming
  def Hamming.compute(a,b)
    len = a.length < b.length ? a.length : b.length
    diff = 0
    len.times {|i| diff +=1 if a[i]!=b[i]}
    return diff
  end
end

#Hamming.compute('GGACG', 'GGTCG')
