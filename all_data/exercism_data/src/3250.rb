def compute strand1, strand2
    strand1.each_char
      .zip(strand2.each_char)
      .count() do |pair|
        pair[0] == pair[1]
      end
  end