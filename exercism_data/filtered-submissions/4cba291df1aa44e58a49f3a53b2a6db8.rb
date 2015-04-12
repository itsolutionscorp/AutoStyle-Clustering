def compute(genetic_code1, genetic_code2)
      shorter_code_length = [genetic_code1.length, genetic_code2.length].min
      hamming_distance = 0
      (0...shorter_code_length).each do |i|
         hamming_distance += 1 if genetic_code1[i] != genetic_code2[i]
      end
      hamming_distance
   end