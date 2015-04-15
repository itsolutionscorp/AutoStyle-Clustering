def compute(dna1, dna2)

    cnt = 0


    for i in 0..dna1.length-1 do




      if(i>=dna2.length)
        break
      end



      if(dna1[i] != dna2[i]) then cnt += 1
      end
    end

    cnt
  end