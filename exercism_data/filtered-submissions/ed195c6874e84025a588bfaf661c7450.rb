def compute(nucleotide_a, nucleotide_b)


   b_arr = nucleotide_b.split('')
   counter = 0
   nucleotide_a.split('').each_with_index do |item, index|
    break if index > b_arr.length - 1
    counter = counter + 1 if item != b_arr[index]
   end
   counter
  end