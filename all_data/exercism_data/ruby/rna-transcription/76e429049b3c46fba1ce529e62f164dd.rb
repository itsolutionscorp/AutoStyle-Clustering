class DNA

  def initialize(dna_input)
    @dna_input = dna_input
  end

  def to_rna
    @dna_input.split("").inject("") { |result, char| result << transcribe(char) }
  end

  private

  def transcribe(letter)
    case letter
    when "C" then cytidine 
    when "G" then guanosine 
    when "A" then adenosine 
    when "T" then thymidine 
    else
      raise "ERROR: Not a valid dna character"
    end
  end
  
  def cytidine
    "C"
  end

  def guanosine
    "G"
  end
  
  def adenosine
    "A"
  end

  def thymidine
    "U"
  end

end
