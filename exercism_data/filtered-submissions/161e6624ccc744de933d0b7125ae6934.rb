def compute(strand1, strand2)
    strand1.chars.zip(strand2.chars).count do |strand1_base, strand2_base|
      strand1_base != strand2_base
    end
  end