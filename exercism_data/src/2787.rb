def compute(s1, s2)
    unless s1.is_a? String and s2.is_a? String
      raise ArgumentError, 'strands must be strings'
    end
    [s1, s2].map(&:size).min.times.count do |n|
      s1[n] != s2[n]
    end
  end