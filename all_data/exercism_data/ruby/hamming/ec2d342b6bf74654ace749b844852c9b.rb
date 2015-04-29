	class Hamming
		def self.compute (dnaStrand1,dnaStrand2)
			return unless dnaStrand1.length == dnaStrand2.length
			$counter=0;
			hamming_distance=0;
			while $counter < dnaStrand1.length
				if dnaStrand1[$counter]!=dnaStrand2[$counter]
					hamming_distance+=1					
				end
				$counter+=1
			end
			return hamming_distance
		end
	end
	puts Hamming.compute('a','b')
