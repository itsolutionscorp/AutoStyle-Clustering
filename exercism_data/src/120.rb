def compute(reference, examinee)
     if reference.length > examinee.length
       run_length = examinee.length
     else
       run_length = reference.length
     end

     run_length -= 1

     hamming_distance = 0

     (0..run_length).each do |string_index|
       if examinee[string_index] != reference[string_index]
         hamming_distance += 1
       end
     end

     hamming_distance
   
   end