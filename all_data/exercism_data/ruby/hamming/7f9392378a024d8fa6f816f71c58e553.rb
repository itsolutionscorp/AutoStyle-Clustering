class Hamming

   class HammingDistance
     def initialize()
       @hamming_distance = 0
     end
     
     def increase()
       @hamming_distance += 1
     end
     
     def report()
       @hamming_distance
     end
   end# of HammingDistance

	def self.compute(strand_a, strand_b)	
	
	# Given two DNA sequences, calculate the number of 
	# different pairs. If the sequences are of equal length, line them up by the 
	# leftmost character and ignore blanks. 
	
		hamming_distance = HammingDistance.new
		length_of_shortest_strand = [strand_a.length, strand_b.length].min
		(0...length_of_shortest_strand).each do |character_index|
		  if strand_a[character_index] != strand_b[character_index] then
			hamming_distance.increase
		  end
		end
		hamming_distance.report
	end# of self.compute
end# of Hamming
    
