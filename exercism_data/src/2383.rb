def compute(strand_1, strand_2)
    count = 0
    strand_1.split("").each_with_index do |chr, index|
      count += 1 if chr != strand_2[index]
    end
    count
  end