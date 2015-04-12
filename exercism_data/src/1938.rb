def compute(strand_a, strand_b)
      minsize = [strand_a, strand_b].map(&:size).min 
      minsize.times.count{|i| strand_a[i] != strand_b[i]}
    end