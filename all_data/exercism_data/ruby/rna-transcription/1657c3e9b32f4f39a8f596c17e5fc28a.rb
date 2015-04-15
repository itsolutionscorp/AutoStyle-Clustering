class Complement 
  def self.of_dna(strand)   
    begin
      DNA.new(strand).complement 
    rescue InvalidStrand
      raise ArgumentError
    end 
  end 

  def self.of_rna(strand)   
    begin 
      RNA.new(strand).complement 
    rescue InvalidStrand
      raise ArgumentError 
    end 
  end 
end 




class InvalidStrand < StandardError; end 
  
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
        raise InvalidStrand
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
        raise InvalidStrand
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
