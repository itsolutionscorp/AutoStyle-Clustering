def compute(strand1, strand2)
    smallest, biggest = if strand1.length<strand2.length
                          [strand1, strand2]
                        else
                          [strand2, strand1]
                        end
    smallest.chars.zip(biggest.chars).select{|x, y| x!=y}.length
  end