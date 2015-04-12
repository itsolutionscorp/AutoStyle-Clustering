def compute(chain1, chain2)
    # Initializes Counter
    hamming = 0
    # Trims both chains to equal length
    if (chain1.length > chain2.length)
      chain1 = chain1[0..chain2.length-1]
    elsif (chain1.length < chain2.length)
      chain2 = chain2[0..chain1.length-1]
    end
    # Compares both chains and increases counter when different
    chain1.split("").each_with_index do |char,i|
      if (char != chain2[i]) && (chain2[i] != "")
        hamming = hamming +1
      end
    end
    return hamming
  end