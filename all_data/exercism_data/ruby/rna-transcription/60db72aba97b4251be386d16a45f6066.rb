def dna_to_rna(dna_strand)
	#puts "Please insert a DNA sequence to be converted to the equivelant RNA strand."
	#dna_strand = gets.chomp.upcase!
	rna_strand = []

	dna_letters = dna_strand.split('').each do |x|
		case x
			when 'A' then rna_strand << 'U'
			when 'C' then rna_strand << 'G'
			when 'G' then rna_strand << 'C'
			when 'T' then rna_strand << 'A'
			else puts "An error occured."
		end
	end
	puts "The RNA compliment of the given DNA strand is: #{rna_strand.join('')}"
end
