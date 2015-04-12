class Hamming
  def Hamming.compute(strand1, strand2)
    strand1.split("").zip(strand2.split("")).select{|x|x[0]!=x[1]}.length
  end
end

print Hamming.compute('GGACGGATTCTG', 'AGGACGGATTCT')

