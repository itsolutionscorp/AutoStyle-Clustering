class Complement
	class << self
		def of_dna(ch)
			map = {
				"C" => "G",
				"G" => "C",
				"T" => "A",
				"A" => "U"
			}
			ch.split("").map{|f| map[f] }.join
		end

		def of_rna(ch)
			map = {
				"G" => "C",
				"C" => "G",
				"U" => "A",
				"A" => "T"
			}
			ch.split("").map{|f| map[f] }.join
		end
	end
end
