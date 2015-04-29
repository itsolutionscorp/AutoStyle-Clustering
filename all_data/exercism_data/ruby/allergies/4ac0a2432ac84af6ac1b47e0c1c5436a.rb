class Allergies

	def initialize(score)
		@score = score
	end
	
	FOOD = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']
	
	def list
		list = []
		unless @score == 0
			sc = @score
			start = sc
			i = 0		
			loop do 
				if Math.log2(sc) % 1 == 0
					list << FOOD[Math.log2(sc).to_int]
					break if sc == 1
					sc = start - sc
					break if sc == 0
					start = sc
				else sc -= 1
				end	
			end
			list = list.compact
		end
		list.reverse!
	end
	
	def allergic_to?(food_item)
		yes_no = false
		log2 = FOOD.index(food_item)
		number_tested = @score - 2 ** log2
		if number_tested == 0
			yes_no = true
		else
			n = 0
			sum2 = 0
			for i in 0..5 do
				n = 2 ** i 
				yes_no = true if number_tested == n
				sum2 = 2 ** i + n
				yes_no = true if number_tested == n 
			end
		end
		yes_no
	end

end
