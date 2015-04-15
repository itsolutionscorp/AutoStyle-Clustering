class Hamming
  def Hamming.compute (strand1, strand2)
    len = [strand1.size, strand2.size].min
    dist=0
    for i in (0...len) do
        dist+= 1 if strand1[i]!=strand2[i]
    end
    dist
  end
end
