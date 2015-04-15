class DNA

  CYTIDINE = "C"
  GUANOSINE = "G"
  ADENOSINE = "A"
  THYMIDINE = "U"

  def initialize(dna_input)
    @dna_input = dna_input
  end

  def to_rna
    @dna_input.split("").inject("") { |result, char| result << transcribe(char) }
  end

  private

  def transcribe(letter)
    case letter
    when "C" then CYTIDINE 
    when "G" then GUANOSINE 
    when "A" then ADENOSINE 
    when "T" then THYMIDINE 
    else
      raise ArgumentError, "ERROR: Not a valid dna character"
    end
  end
  
end
