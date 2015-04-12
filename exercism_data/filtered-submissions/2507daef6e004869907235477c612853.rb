def compute(seq_a, seq_b)
    
    distance = 0
    
    seq_a.scan(/./).zip(seq_b.scan(/./)).each {|a,b| distance += 1 if a != b}
    
    distance
    
  end