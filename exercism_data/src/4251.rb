def compute(string1, string2)
		amount = 0
		string1.each_char.with_index do |s, i|
			amount += 1 if s != string2[i] && string2[i] != nil
		end
                amount
	end