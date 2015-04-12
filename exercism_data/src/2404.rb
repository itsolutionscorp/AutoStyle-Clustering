def compute(dna_strand_one, dna_strand_two, count = 0)
    strand_one = dna_strand_one.split("")
    strand_two = dna_strand_two.split("")
    strand_one.each_with_index do |item, index|
      if item != nil && strand_two[index] != nil
        count += 1 if item != strand_two[index]
      else
        break
      end
    end
    count
  end