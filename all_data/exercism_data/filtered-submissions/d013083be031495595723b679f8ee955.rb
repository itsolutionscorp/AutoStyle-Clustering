def compute(gene_1,gene_2)
    hamming_number = 0
    if gene_1 == gene_2
      return hamming_number
    elsif gene_1.length != gene_2.length
      return -1
    else

      for i in 0..gene_1.length
        if gene_1[i] != gene_2[i] then
          hamming_number += 1
        end
      end
      return hamming_number
    end
  end