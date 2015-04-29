class Complement 
  class << self 
    def of_dna(strand)
	    if strand.match(/[^GCTA]/) 
		    fail 'Not a valid dna base'
	    else
		    strand.tr('GCTA','CGAU') 
	    end
    end

    def of_rna(strand)
	    if strand.match(/[^CGAU]/)
		    fail 'Not a valid rna base' 
	    else
		    strand.tr('CGAU','GCTA') 
	    end
    end
  end
end
