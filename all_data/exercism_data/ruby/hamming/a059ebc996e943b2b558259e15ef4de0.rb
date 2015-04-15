class Hamming
  def self.compute(first_strand, second_strand)
    distance = 0
    first_strand.chars.each_with_index do |char, index|
      if second_strand[index] && char != second_strand[index]
        distance +=1 
      end
    end
    distance
  end
end
