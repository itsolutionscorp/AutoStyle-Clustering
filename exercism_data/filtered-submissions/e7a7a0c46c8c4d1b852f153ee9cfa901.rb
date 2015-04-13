def compute(arg1,arg2)
		return 0 unless arg1.length == arg2.length

		a1 =  arg1.split("")
		a2 = arg2.split("")
		count = 0
		i = 0
		a1.length.times do
			unless a1[i] == a2[i]
				count = count + 1
			end
		end
		return count
	end