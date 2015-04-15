class Numeric
	CONVERSION_ARRAY = [[1000, 'M'],[500,'D'],[100, 'C'],[50 ,'L'],[10,'X'],[5 ,'V'],[1 ,'I']]

	def to_roman
		numValues = CONVERSION_ARRAY.size
		returnString = ""
		tempValue = self

		(0..numValues-1).each do | i |
			index = CONVERSION_ARRAY[i][0]
			value = CONVERSION_ARRAY[i][1]

			newValue = tempValue - index

			if (i < numValues-1) && (index / CONVERSION_ARRAY[i+1][0]) == 5
				divByValue = (index / 5)
			else
				divByValue = (index / 10)
			end

			if newValue < 0
				#the current array value is too big, but see if it is a special case like 4, 9, 40, etc.
				if newValue.abs <= divByValue
					remainder = tempValue % divByValue
					newValue = tempValue - remainder
					if (newValue - index).abs == divByValue
						return returnString + divByValue.to_roman + value.to_s + remainder.to_roman
					end
				end
			elsif newValue > 0
				returnString += value.to_s
				tempValue = newValue
				redo
			else
				#no more values
				return returnString + value.to_s
			end
		end
		return returnString
	end
end
