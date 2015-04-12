def compute(strand1, strand2)
    if strand1.length != strand2.length
      abort
    end
    hamming_count = 0
    strand1.chars.zip(strand2.chars).each {|val| hamming_count += 1 unless val[0] == val[1]}
    hamming_count
  end