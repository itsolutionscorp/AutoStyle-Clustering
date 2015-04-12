def compute( firts , secon )
		compare = first.chars.zip(secon.chars)
		compare.select { |par| !par[0].eql? par[1] }.count
	end