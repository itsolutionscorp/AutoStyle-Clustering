def compute(input1, input2)
    attr_accessor :hamming_distance, :max_length

    hamming_distance = 0
    dna1 = input1.split('')
    dna1_length = dna1.length
    dna2 = input2.split('')
    dna2_length = dna2.length

    if dna1_length == dna2_length
    	max_length = dna1_length
    else
      dna1_length < dna2_length ? max_length = dna1_length : max_length = dna2_length
    end

    (0...max_length).each do |i|
      if dna1[i] != dna2[i]
        hamming_distance+=1
      end
    end
    hamming_distance
  end