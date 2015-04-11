class DNA
  def initialize(dna)
    @@original_string = dna

  end

  def hamming_distance(secondary_string)
    #returns a number indicating the number of base-pair mismatches

    errors = 0
    dna_1 = chars_to_array(@@original_string)
    dna_2 = chars_to_array(secondary_string)

    #sets the shortest sequence, as that is all that can be tested
    shorter = dna_1.length < dna_2.length ? dna_1 : dna_2

    (0..shorter.length-1).each do |i|
      errors += 1 if dna_1[i] != dna_2[i]
    end
   
    return errors 
  end

  private

  def chars_to_array(string)
    return string.split(//)
  end


end
