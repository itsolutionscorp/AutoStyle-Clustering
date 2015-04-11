# need to go through each index of each array, determine if they are the same:
# if they are the same - do not increase counter
# if they are different - increase counter
# return counter at the end

class Hamming


  def self.compute(original, duplicate)
    original_dna = self.string_to_array(original)
    duplicate_dna = self.string_to_array(duplicate)
    if self.check_length(original_dna, duplicate_dna)
      self.check_similarity(original_dna, duplicate_dna)
    else
      self.check_similarity(duplicate_dna, original_dna)
    end
  end

  def self.check_length(original, duplicate)
    original.length < duplicate.length
  end

  def self.string_to_array(array)
    array.split("")
  end

  def self.check_similarity(dna_string, duplicate)
    counter = 0
    dna_string.each_with_index do |letter, index|
      if letter == duplicate[index]
        next
      else
        counter += 1
      end
    end
    return counter
  end

end
