def combine_anagrams(words)
	map = Hash.new
	g = Array.new
	index = 0;
	words.each{ |w|
		if map[w.downcase.split("").sort.join].nil?
			map[w.downcase.split("").sort.join] = index

			g[map[w.downcase.split("").sort.join]] = Array(w)
			
			index = index + 1
		else
			g[map[w.downcase.split("").sort.join]] << w
		end
	}
	
	g
end

