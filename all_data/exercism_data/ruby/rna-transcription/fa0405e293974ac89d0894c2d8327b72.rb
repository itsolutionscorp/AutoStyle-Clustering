# Create Complement module
class Complement
	# Create of_dna method
	def self.of_dna(strand)
		# Init index (i) and complement string (complement)
		i = 0
		complement = ""
		# Complement cases
		while i != strand.length
			case strand[i]
			when "A"
				complement << "U"
			when "T"
				complement << "A"
			when "G"
				complement << "C"
			when "C"
				complement << "G"
			else
				puts "Not a valid DNA nucleobase!"
			end

			i += 1
		end

		return complement
	end

	# Create of_rna method
	def self.of_rna(strand)
		# Init index (i) and complement string (complement)
		i = 0
		complement = ""
		# Complement cases
		while i != strand.length
			case strand[i]
			when "A"
				complement << "T"
			when "U"
				complement << "A"
			when "G"
				complement << "C"
			when "C"
				complement << "G"
			else
				puts "Not a valid RNA nucleobase!"
			end

			i += 1
		end

		return complement
	end
end
