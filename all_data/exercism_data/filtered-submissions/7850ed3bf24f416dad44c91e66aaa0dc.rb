def compute(chain1, chain2)

    hamming = 0

    if (chain1.length > chain2.length)
      chain1 = chain1[0..chain2.length-1]
    elsif (chain1.length < chain2.length)
      chain2 = chain2[0..chain1.length-1]
    end

    chain1.split("").each_with_index do |char,i|
      if (char != chain2[i]) && (chain2[i] != "")
        hamming = hamming +1
      end
    end
    return hamming
  end