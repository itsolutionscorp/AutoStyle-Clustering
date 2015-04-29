def compute(dna1, dna2)

    count = 0


    for i in 0..dna1.length-1 do




      if (i >= dna2.length) then
        break
      end



      count = if (dna1[i] != dna2[i]) then count + 1 else count end
    end

    count
  end