def compute(first, second)
      if first.length <= second.length
        small_strand, large_strand = first, second
      else
        small_strand, large_strand = second, first
      end
      count = 0
      small_strand.split('').each_with_index do |value, index|
        count+= 1 if value != large_strand[index]
      end
      #count
    end