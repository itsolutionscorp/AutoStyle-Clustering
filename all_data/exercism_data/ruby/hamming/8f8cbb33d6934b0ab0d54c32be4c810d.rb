class Hamming
  def self.compute(a, b) 
    original = a.chars
    other = b.chars
    normalize(original, other)
    #return a count of how many elemets are different
    (0...@amount_to_compare).count do |e| 
      original[e] != other[e]
    end
  end
  #determine how many elements to iterate over
  def self.normalize(array1, array2)
    if array1.count > array2.count
      @amount_to_compare = array2.count
    elsif array2.count > array1.count
      @amount_to_compare = array1.count
    else
      #could be either as they are the same size
      @amount_to_compare = array1.count   
    end
  end
end
