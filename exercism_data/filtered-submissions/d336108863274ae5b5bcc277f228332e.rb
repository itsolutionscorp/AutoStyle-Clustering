def compute strand1, strand2

    chars1 = strand1.chars
    chars2 = strand2.chars

    paired_array = chars1.zip(chars2)

    paired_array.count do |first, second|
      first && first != second
    end

  end