def compute( input_A, input_B )
    count_same_char = 0
    input_A.size.times do |index|
      count_same_char +=1 unless (input_A[index] == input_B[index])
    end
    count_same_char
  end