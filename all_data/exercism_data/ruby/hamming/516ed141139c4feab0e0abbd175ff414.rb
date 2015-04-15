class Hamming
  def self.compute(a,b)
    distance = 0
    c = a.split(//).zip b.split(//)

    c.each do | item1,item2 | 
      break if item1.nil? || item2.nil?
      distance=distance+1 if (item1 != item2)
    end
    distance
  end
end
