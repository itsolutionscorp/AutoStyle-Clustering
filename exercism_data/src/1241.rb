class Hamming

  def compute left, right

    if left.length < right.length
      shorter, longer = left.chars, right.chars
    else
      shorter, longer = right.chars, left.chars
    end
    
    shorter.zip(longer).count { |pair| pair.first != pair.last } 
  end

end
