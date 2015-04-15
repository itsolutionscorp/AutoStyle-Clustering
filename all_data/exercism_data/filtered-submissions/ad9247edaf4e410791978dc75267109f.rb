def compute(genome1,genome2)
    min_length = [genome1.length, genome2.length].min
    h_score = 0
    (0..min_length-1).each do |i|
      h_score+=1 unless genome1[i]==genome2[i]
    end
    h_score
  end