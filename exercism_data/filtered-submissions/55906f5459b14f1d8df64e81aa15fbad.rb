def compute(a_strand, b_strand)
    bytepairs = a_strand.bytes.zip(b_strand.bytes)
    bytepairs.select{|pair| pair.compact.uniq.length > 1 }.length
  end