def compute(input1, input2)
    strand1 = input1.chars
    strand2 = input2.chars

    length = [strand1.length, strand2.length].min

    length.times.map do |n|
      strand1[n] == strand2[n] ? 0 : 1
    end.inject(&:+)
  end