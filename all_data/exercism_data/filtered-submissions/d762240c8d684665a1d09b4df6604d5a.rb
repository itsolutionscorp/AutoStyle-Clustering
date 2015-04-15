def hamming(strand_1, strand_2)

  strand_1_arry = strand_1.split("")
  strand_2_arry = strand_2.split("")

if strand_1.length == strand_2.length && strand_1 != strand_2
    hamm = []

    hamm = strand_1_arry - strand_2_arry
      if (strand_1_arry - strand_2_arry) == strand_1_arry
        strand_1.length
      else
        hamm.length
      end
 elsif (strand_1.length - strand_2.length) >= 0
   pop_diff = strand_1.length - strand_2.length

   strand_1_arry.pop(pop_diff)
   hamm = strand_1_arry - strand_2_arry
   hamm.length
 elsif (strand_2.length - strand_1.length) >= 0
   pop_diff = strand_2.length - strand_1.length

   strand_2_arry.pop(pop_diff)
   hamm = strand_2_arry - strand_1_arry
   hamm.length
 end
end
