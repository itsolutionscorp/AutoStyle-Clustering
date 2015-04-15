class Hamming
  def self.compute(s_one, s_two)
  	a = s_one.chars
  	b = s_two.chars
  	c = a.length

  	c.times.count do |val|
  	 a[val] != b[val]
  	end
  end
end
