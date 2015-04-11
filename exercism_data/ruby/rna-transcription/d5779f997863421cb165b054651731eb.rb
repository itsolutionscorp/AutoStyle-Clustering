class Complement
  def self.of_dna(args)
  	dna = args.gsub('G', '1').gsub('C', '2').gsub('T', '3').gsub('A', '4')
  	puts dna
  	dna.gsub('1', 'C').gsub('2', 'G').gsub('3', 'A').gsub('4','U')
 
  end
def self.of_rna(args)
  	rna = args.gsub('C', '2').gsub('G', '1').gsub('A', '3').gsub('U', '4')
  	puts rna
  	rna.gsub('1', 'C').gsub('2', 'G').gsub('3', 'T').gsub('4','A')
  end
end

#
