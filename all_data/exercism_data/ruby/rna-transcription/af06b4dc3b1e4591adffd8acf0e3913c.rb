class Complement  

  def self.of_dna(strand)   
    DNA.new(strand).complement 
  end 

  def self.of_rna(strand)   
    RNA.new(strand).complement 
  end  
end 

 
class RNA
  
  RNAtoDNA = {
    'C' => 'G',
    'G' => 'C',
    'A' => 'T',
    'U' => 'A'
  }

  def initialize(strand) 
    strand.upcase! 
    @rna_strand = proofread(strand)
  end  

  def complement
    transcribe
  end 

  private 

  def proofread(strand) 
    strand.each_char do |nucleotide| 
      if not transcriptable?(nucleotide)
        raise ArgumentError
      end  
    end
  end 

  def transcriptable?(nucleotide) 
    RNAtoDNA.has_key? nucleotide
  end  

  def transcribe
    result = '' 
    @rna_strand.each_char do |nucleotide|    
      result << RNAtoDNA[nucleotide]
    end  
    result
  end     
end # end class


class DNA 

  DNAtoRNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  } 
  
  def initialize(strand) 
    strand.upcase! 
    @dna_strand = proofread(strand)
  end  

  def complement
    transcribe
  end 

  private 

  def proofread(strand) 
    strand.each_char do |nucleotide| 
      if not transcriptable?(nucleotide)
        raise ArgumentError
      end  
    end
  end 

  def transcriptable?(nucleotide) 
    DNAtoRNA.has_key? nucleotide
  end  

  def transcribe
    result = '' 
    @dna_strand.each_char do |nucleotide|    
      result << DNAtoRNA[nucleotide]
    end
    result 
  end     
end # end class
