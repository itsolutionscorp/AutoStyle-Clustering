class Hamming

  def self.to_array string
    string.split ""
  end

  def self.fold elems, start
    result = start
    elems.each do |e|
      result = yield result, e
    end
    result
  end

  def self.compute left, right
    left_seq  = Hamming.to_array left    
    right_seq = Hamming.to_array right 
    zipped    = left_seq.zip right_seq  

    Hamming.fold zipped, 0 do |acc, pair| 
      if pair[0] == pair[1]
        acc
      else
        acc + 1
      end
    end
  end

end
