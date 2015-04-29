class Hamming

  def self.compute(sequence1, sequence2)
    count = 0
    array1 = sequence1.chars
    array2 = sequence2.chars
    joined = array1.zip(array2)
    joined.each do |key, value|
      if key != value
        count += 1
      end
    end
    count
  end

end
