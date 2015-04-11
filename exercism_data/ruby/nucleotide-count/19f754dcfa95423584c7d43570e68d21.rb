class DNA
  def initialize(nucleotides)
      @nucleotide = nucleotides.split("") 
  end

  def count(char)
    if isValid(char) == false
      raise ArgumentError
    else
      num = 0
      @nucleotide.each do |tide|
        if tide == char
          num += 1
        else
          num = num
        end
      end
      num
    end
  end

  def nucleotide_counts()
    nucleotide_hash= {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
      @nucleotide.each do| tide |
      nucleotide_hash[tide] ||= 0
      nucleotide_hash[tide]+=1
      end
      nucleotide_hash
  end

  def isValid(char)
    if char == 'A' || char == 'T' || char == 'C' || char == 'G' || char == 'U'
      true
    else
      false
    end
  end
end
