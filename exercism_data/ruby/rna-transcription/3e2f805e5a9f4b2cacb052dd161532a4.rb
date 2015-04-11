class Complement

 def self.of_dna(strand)
	strand.tr!('CGTA', 'GCAU')
 end

 def self.of_rna(strand)
	strand.tr!('CGAU', 'GCTA')
 end
end










#	for i in strand.length
#		case strand[i]
#		when 'G'
#			strand[i]='C'
