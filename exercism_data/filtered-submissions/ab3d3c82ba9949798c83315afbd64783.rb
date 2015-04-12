def compute(input_a, input_b)
        distance = 0
        first_array = input_a.split("")
        second_array = input_b.split("")

        first_array.each_with_index do |character, index|
    		if second_array[index] != character && second_array[index] != nil
    			distance += 1
    		end
        end

        distance
    end