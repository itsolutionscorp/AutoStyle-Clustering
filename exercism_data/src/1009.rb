def compute(strand, strand2)
    result = 0
    strand.split('').each_with_index do |letter, i|
      result += 1 unless letter == strand2[i]
    end
    result
  end