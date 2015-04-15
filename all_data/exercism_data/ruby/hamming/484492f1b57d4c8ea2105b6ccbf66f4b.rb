class Hamming
  def self.compute(first_strand, second_strand)
    distance=0
    first_strand.scan(/./).each_with_index do |character,index|
      distance+=1 if character!=second_strand[index]
    end
    distance
  end

end
