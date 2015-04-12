class Hamming
	def compute(input, reference)
		arr_input = input.split("")
		arrr_ref = reference.split("")
		counter_diff = 0
		for i in 0...[arr_input.length,arrr_ref.length].min
			if arr_input[i] != arrr_ref[i]
				counter_diff += 1
			end
		end
		return counter_diff
	end
end
