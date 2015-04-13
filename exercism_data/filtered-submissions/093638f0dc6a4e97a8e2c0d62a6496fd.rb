def compute ham1, ham2
    size = [ham1, ham2].map(&:size).min

    ham1.chars.first(size).zip(ham2.chars.first(size)).count do |(a,b)|
      !a.eql?(b)
    end
  end