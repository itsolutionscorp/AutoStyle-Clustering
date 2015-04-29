def compute(strand1, strand2)
    strand1.chars.zip(strand2.chars).inject(0) do |distance, (base1, base2)|
      break distance unless base1 && base2
      base1 == base2 ? distance : distance + 1
    end
  end