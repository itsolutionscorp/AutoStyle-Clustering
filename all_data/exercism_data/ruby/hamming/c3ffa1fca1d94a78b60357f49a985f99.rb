module Hamming

  def self.compute(first, second)
    counter = 0
    i = 0
    size = self.shortest(first,second).size
    while i < size
      counter += 1 if first[i]!=second[i]
      i += 1
    end
    counter
  end
    
  def self.shortest(first,second)
    [first,second].sort {|x,y| x.size <=> y.size}[0]
  end

end
