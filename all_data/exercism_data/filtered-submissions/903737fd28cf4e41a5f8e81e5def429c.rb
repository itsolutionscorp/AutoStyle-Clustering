def compute(strand_a, strand_b)
      raise "Strands must be similar length" unless strand_a.length == strand_b.length
      a = strand_a.bytes
      b = strand_b.bytes
      diff = a.select.with_index{|byte, index| byte != b[index]}
      diff.count
    end