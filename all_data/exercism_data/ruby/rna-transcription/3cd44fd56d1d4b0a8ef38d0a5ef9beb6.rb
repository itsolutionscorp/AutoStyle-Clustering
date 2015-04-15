class Complement
  class << self
	  # @replace_hash = {'G' => 'C', 'C' => 'G', 'T' => 'A',  'A' => 'U'}
	  def of_dna(dna)
	  	# dna.chars.reduce("") {|x, y| x << @replace_hash[y]}
	    dna.tr('GCTA', 'CGAU')
	  end

	  def of_rna(rna)
	  	# rna.chars.reduce("") {|x, y| x << @replace_hash.key(y)}
	    rna.tr('CGAU', 'GCTA')
	  end
  end
end
