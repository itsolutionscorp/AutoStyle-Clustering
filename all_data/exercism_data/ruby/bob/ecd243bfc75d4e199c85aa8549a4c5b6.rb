class DNA

  def initialize(dna_input)
    @dna_input = dna_input
  end

  def to_rna
    rna = ""
    @dna_input.each_char do |char|
      rna << transcribe(char)
    end
    rna
  end

  private

  def transcribe(letter)
    case letter
    when "C"
     transcribe_cytidine 
    when "G"
     transcribe_guanosine 
    when "A"
     transcribe_adenosine 
    when "T"
     transcribe_thymidine 
    else
      raise "ERROR: Not a valid dna character"
    end
  end
  
  def transcribe_cytidine
    "C"
  end

  def transcribe_guanosine
    "G"
  end
  
  def transcribe_adenosine
    "A"
  end

  def transcribe_thymidine
    "U"
  end

end
