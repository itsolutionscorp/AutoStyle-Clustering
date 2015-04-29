class Complement 
  class << self 
    def of_dna(strand)
      strand_array = strand.split(//)
      strand_array.map! do |x|
	     case x 
	       when 'G'
		      'C' 
	       when 'C' 
		       'G'
	       when 'T' 
		       'A'
	       when 'A'
		       'U'
	       else 
		       'Not a valid dna base'
	     end
      end
      rna = strand_array.join
    end

    def of_rna(strand) 
      strand_array = strand.split(//)
      strand_array.map! do |x| 
        case x 
	  when 'C'
		 'G' 
	  when 'G'
		  'C'
	  when 'A'
		  'T'
	  when 'U'
		  'A'
	  else 
		  'Not a valid rna base'
	end
      end
      dna = strand_array.join    
    end
  end
end
