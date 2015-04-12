def compute(s1, s2)
    strand1 = s1.split ""
    strand2 = s2.split ""
    distance = 0

    strand1.zip(strand2).each do |s1, s2|
      distance += 1 unless s1 == s2
    end

    distance

  end