class Year
	def self.leap? year
		even_four = (year % 4)
		even_hundred = (year % 100)
		even_four_hundred = (year % 400)

		if even_four == 0 && even_hundred != 0 then true
		elsif even_four == 0 && even_four_hundred == 0 then true
		end
	end
end
