def compute(strand_one, strand_two)
    d, i = 0, 0
#    strand_one.split('').each_with_index do |s1, i| #using this as oposed to the line below runs slightly slower
    strand_one.chars do |s1|
      if s1 != strand_two[i]
        d += 1
      end
        i += 1
    end
    d
  end