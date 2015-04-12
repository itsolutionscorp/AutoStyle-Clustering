class Hamming

  def compute(strand1, strand2)
    result = 0
    position = 0
    strand1.each_char do |char|
      if char != strand2[position]
        result += 1
      end
      position += 1
    end
    result 
  end

end
