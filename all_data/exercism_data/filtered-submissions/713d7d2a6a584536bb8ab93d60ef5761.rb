def compute(strand1, strand2)
      index = -1
      strand1.chars.inject(0) do |distance, char|
        index += 1
        (strand2[index] == char || strand2[index] == nil) ? distance : distance + 1
      end
    end