class Hamming
  def self.compute(original, mutant)
    result = 0
    original.split("").each_with_index do |letter, index|
      result+=1 if letter!=mutant[index] && index < mutant.length
    end
    return result
  end
end
