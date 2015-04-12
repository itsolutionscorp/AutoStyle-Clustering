def compute(strand1, strand2)
    chars1 = strand1.chars
    chars2 = strand2.chars
    iterate = [chars1.length,chars2.length].min
    distance = 0
    iterate.times do |n|
      distance += 1 unless chars1[n] == chars2[n]
    end
    distance
  end