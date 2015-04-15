class Hamming
	def self.compute(one, two)
	  first = one.split('')
    second = two.split('')
    count = 0
    first.zip(second).each  do |x, y|
      if x != y
        count += 1
      end
    end
    count
	end
end
