def compute dna1, dna2
    dna1_chars = dna1.chars  
    dna2_chars = dna2.chars

    if dna1_chars.size <= dna2_chars.size
      shorter = dna1_chars
      longer  = dna2_chars
    else
      shorter = dna2_chars
      longer  = dna1_chars
    end

    result = shorter.each_with_index.map {|x,i| x == longer[i] ? 0 : 1 } 
    result.reduce {|x, y| x + y} || 0

  end