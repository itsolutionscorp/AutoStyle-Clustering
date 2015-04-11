class Hamming

  def self.compute x, y
    count = 0
    larger_sequence, smaler_sequence = self.sequences_by_size(x,y)
    smaler_sequence.split('').each_with_index do |val, index| 
      count+=1 if larger_sequence[index] != val
    end
    count
  end

  def self.sequences_by_size x, y
    if x.length > y.length
      return x,y
    else
      return y,x
    end
  end


end
