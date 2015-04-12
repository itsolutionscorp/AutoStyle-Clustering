def compute(first_strand, second_strand)
    first_split = first_strand.split("")
    second_split = second_strand.split("")
    address = 0
    hamming = 0
    first_split.each do |x|
      if x != second_split[address]
        hamming += 1
      end
      address +=1
    end
    hamming
  end