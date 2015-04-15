def combine_anagrams(words)
	a_words = words.join(' ').gsub(/\W/,' ').split
	a_vistos = []	
	a_ret = []

	a_words.each do |i|

		str_down = i.downcase.chars.sort.join

		if a_vistos.include?(str_down)
			next
		end

		a_iguais = a_words.select { |x| x.downcase.chars.sort.join == str_down }

		puts a_iguais.to_s + ' bla ' + i.to_s
		puts i.class

		a_vistos.push(str_down)
		a_nao_vistos = a_words.select { |x| !(a_vistos.include?(x.downcase.chars.sort.join)) }

		a_nao_vistos.each do |j|
			if j.downcase.chars.sort.join == str_down
				a_iguais << j
				a_vistos.push(j.downcase.chars.sort.join)
			end
		end

		a_ret << a_iguais

	end

	a_ret

end

# ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']