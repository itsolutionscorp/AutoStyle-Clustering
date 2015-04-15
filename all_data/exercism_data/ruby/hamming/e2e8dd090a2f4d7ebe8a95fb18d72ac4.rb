class Hamming
  
  
  def self.compute(a,b)
    a.split('').zip(b.split('')).map {|x,y| x==y || x==nil || y == nil}.count(false)
  end
  
  
  # def self.compute(strand_a,strand_b)
  #   min_l = [strand_a.length,strand_b.length].min
  #   hamming_distance = 0
  #   min_l.times do |i|
  #       hamming_distance +=1 unless strand_a[i]==strand_b[i]
  #   end  
  #   hamming_distance
  # end  
  
  
  
end  
