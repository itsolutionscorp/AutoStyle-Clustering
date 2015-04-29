def compute(strand1,strand2)


    if strand1.length === strand2.length

      [strand1.chars, strand2.chars].transpose.select { |chars1, chars2| chars1 != chars2}.count
    end

  end