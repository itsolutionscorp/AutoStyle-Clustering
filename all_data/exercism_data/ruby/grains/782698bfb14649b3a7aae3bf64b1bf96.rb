class Grains

	def square (position, amount = 1, currentposition = 1)
		if currentposition == position
			return amount
		end

		return square(position, amount * 2, currentposition + 1)
	end

	def total (totalamount = 0, amount = 1, currentposition = 1)
		if currentposition == 65
			return totalamount
		end

		return total(totalamount + amount, amount * 2, currentposition + 1)
	end
end
