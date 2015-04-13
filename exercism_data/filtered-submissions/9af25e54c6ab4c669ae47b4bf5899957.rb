def compute ham1, ham2
    size = [ham1, ham2].map(&:size).min

    ham1.chars.first(size).zip(ham2.chars.first(size)).reduce(0) do |sum,(a,b)|
      sum + (a.eql?(b) ? 0 : 1)
    end
  end