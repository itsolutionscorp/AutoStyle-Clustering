def compute(strand_1, strand_2)



    if strand_1.length < strand_2.length
      short = strand_1.chars
      long = strand_2.chars
    else
      short = strand_2.chars
      long = strand_1.chars
    end

    count = 0



    short.each_with_index do |c,i|
      count += 1 unless c == long[i]
    end

    count