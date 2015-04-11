class Raindrops
	def self.convert(num)
		if num % 3 == 0 && num % 5 == 0 && num % 7 == 0
			p 'PlingPlangPlong'
		elsif num % 3 == 0 && num % 5 == 0
			p 'PlingPlang'
		elsif num % 3 == 0 && num % 7 == 0
			p 'PlingPlong'
		elsif num % 5 == 0 && num % 7 == 0
			p 'PlangPlong'
		elsif num % 3 == 0
			p 'Pling'
		elsif num % 5 == 0
			p 'Plang'
		elsif num % 7 == 0
			p 'Plong'
		else
			p num.to_s
		end
	end
end
