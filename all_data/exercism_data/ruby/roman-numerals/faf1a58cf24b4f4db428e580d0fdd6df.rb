class Fixnum
	def to_roman
		arabic = self
		roman = ""

		r_tens = ["M","C","X","I"]
		a_tens = [1000,100,10,1]

		r_fives = ["D","L","V"]
		a_fives = [500,50,5]

		3.times do |i|
			while arabic >= a_tens[i]-a_tens[i+1]
				if arabic < a_tens[i]
					roman += r_tens[i+1]
					arabic += a_tens[i+1]
				end
				(arabic / a_tens[i]).times { roman += r_tens[i] }
				arabic = arabic % a_tens[i]
			end
			

			if arabic >= a_fives[i]-a_tens[i+1]
				if arabic < a_fives[i]
					roman += r_tens[i+1]
					arabic += a_tens[i+1]
				end
				roman += r_fives[i]
			end
			arabic = arabic % a_fives[i]
		end

		arabic.times { roman += "I" }
		roman
	end
end
