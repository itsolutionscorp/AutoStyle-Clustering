def compute(s1, s2)
    mutations = 0
    shorter_strand = [s1.length, s2.length].min
    (0...shorter_strand).each do |base|
      mutations +=1 unless s1[base] == s2[base]
    end
    mutations
  end