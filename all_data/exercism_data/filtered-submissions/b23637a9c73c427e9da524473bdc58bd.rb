def compute(strand1, strand2)
    (0..strand1.length).map do |i|
       (strand1[i] != strand2[i]) ? 1 : 0
    end.reduce(:+)
  end