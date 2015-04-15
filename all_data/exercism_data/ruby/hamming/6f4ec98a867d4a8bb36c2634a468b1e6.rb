class Hamming
  def self.compute(a1, a2)
  	r = 0
  	a1.split('').zip(a2.split('')).each{ |i,j| r += 1 unless i == j or i.nil? or j.nil? }
    return r
  end
end
