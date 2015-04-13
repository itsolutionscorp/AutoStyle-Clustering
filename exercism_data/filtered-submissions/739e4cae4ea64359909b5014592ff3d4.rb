def compute strand_1, strand_2
    deviation_count = 0
    [strand_1.length, strand_2.length].min.times do |i|
      deviation_count += 1 if strand_1.split('')[i] != strand_2.split('')[i]
    end
    return deviation_count
  end