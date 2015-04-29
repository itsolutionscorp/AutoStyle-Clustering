class Hamming
  def self.compute(a,b)
    diff = 0
    a.split("").zip(b.split("")).each do |x,y|
      diff +=1 if x != y
    end
    diff
  end
end
