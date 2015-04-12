def compute(strand, compare)
    count = 0
    bases = strand.split("")
    bases.each_with_index do |base, i|
      break unless compare[i]
      count += 1 unless base == compare[i]
    end
    count
  end