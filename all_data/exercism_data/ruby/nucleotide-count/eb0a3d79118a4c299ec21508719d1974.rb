class DNA
  def initialize(sequence)
    @sequence = sequence.split("")
    @letters = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    @uracil = 'U'
  end

  def count(nucleotide)
    if !@letters.has_key?(nucleotide) 
      if nucleotide == @uracil
        return 0   
      else
        raise ArgumentError
      end 
    else
      number = 0
      @sequence.each do |letter|
        if letter == nucleotide
          number += 1
        end
      end
      number
    end
  end

  def nucleotide_counts
    @sequence.each do |letter|
      @letters[letter] += 1
    end
    @letters
  end
end
