def compute left, right
    left.chars.zip(right.chars).inject(0) do |total, pair|   
      pair.last.nil? || pair.first == pair.last ? total : total + 1 
    end 
  end