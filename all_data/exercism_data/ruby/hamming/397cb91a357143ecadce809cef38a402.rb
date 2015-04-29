class Hamming

  def self.compute(a,b)
    counter = 0
    array_1 = a.split //
    array_2 = b.split //
    array_1 = self.set_length(array_1, array_2)
    
    array_1.each_with_index do |elem, index|
      if elem != array_2[index]
        counter += 1
      end
    end
    counter
  end

  # handles cases when array_1 is longer
  def self.set_length ar_1, ar_2
    if ar_1.length > ar_2.length
      ar_1.take(ar_2.length)
    else
      ar_1
    end
  end
end
