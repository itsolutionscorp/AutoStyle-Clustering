class Raindrops<Prime 

	DROPS = {
		3 => 'Pling', 
		5 => 'Plang',
		7 => 'Plong'
	}

	def self.convert(num)
		pf = DROPS.select{ |prime, value| (num % prime) == 0 }.values.join

        pf.empty? ? num.to_s : pf
		end
end
