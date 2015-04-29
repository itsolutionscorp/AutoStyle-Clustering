class DNA

  def initialize(dna_string)
    @dna_string = dna_string.split("")
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
      @dna_string.each do |letter|
        if letter == nucleotide
          number += 1
        end
      end
      number
    end
  end

  def nucleotide_counts
    @dna_string.each do |letter|
      @letters[letter] += 1
    end
    @letters
  end
end
#def word_count
    #word_value = {}
    #@phrase.each do |word|
    #  if !word_value[word].nil?
    #    word_value[word] += 1
    #  else
     #   word_value[word] = 1
    #    end
   # end
   # word_value
 # end
