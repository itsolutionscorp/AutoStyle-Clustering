def compute(seq_string, seq2_string)
    seq_array  =  seq_string.rjust(seq2_string.size).chars
    seq2_array =  seq2_string.ljust(seq_string.size).chars
    if seq_string.size == seq2_string.size
      seq_array.select.with_index { |aa, index| seq2_array[index] != aa }.size
    else
      seq_array.select.with_index { |aa, index| seq2_array[index] == aa }.size - 1
    end  
  end