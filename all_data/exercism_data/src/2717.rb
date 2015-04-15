def compute(strand_a,strand_b)
    min_l = [strand_a.length,strand_b.length].min
    hamming_distance = 0
    min_l.times do |i|
        hamming_distance +=1 unless strand_a[i]==strand_b[i]
    end  
    hamming_distance
  end