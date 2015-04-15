def compute(cat, dog)
		dog_array = dog.chars
		cat_array = cat.chars
		count = 0
		sum = 0
		while count < dog_array.length

		if dog_array[count] != cat_array[count]
			sum += 1
		end
			count +=1
		end
		return sum
    end