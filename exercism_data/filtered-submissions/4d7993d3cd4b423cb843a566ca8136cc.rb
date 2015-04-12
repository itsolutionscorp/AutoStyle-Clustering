def compute(base1, base2)
    nucleotides1 = base1.split("")
    nucleotides2 = base2.split("")
    score = 0
    nucleotides1.each_with_index do |n1_base, i|
      if n1_base != nucleotides2[i]
        score += 1
      end
    end
    score
  end