def DNA(dna)
	dna.split(//)
end

def compare(first,second)
diff = 0

	if first.length === second.length
		length = first.length
		length.times do |i|			
			if first[i] != second[i]
				diff += 1
			end
		end
		puts "The difference is: #{diff}"
	else
		puts "The DNA strings are not the same length."		
	end
	
end

puts "Enter DNA string? "
dna_one = gets.chomp

puts "Enter DNA String 2? "
dna_two = gets.chomp

first = DNA(dna_one)
second = DNA(dna_two)

compare(first,second)
