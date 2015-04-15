def compute(strand_a,strand_b)
    min_l = [strand_a.length,strand_b.length].min
    a_list = strand_a.slice(0,min_l).split('')
    b_list = strand_b.slice(0,min_l).split('')
    hamming_distance = 0
    min_l.times do |i|
        hamming_distance +=1 unless a_list[i]==b_list[i]
    end  
    hamming_distance
  end