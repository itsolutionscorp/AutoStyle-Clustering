def compute(s1, s2)
    unless s1.is_a? String and s2.is_a? String
      raise ArgumentError, 'strands must be strings'
    end
    s1_array = s1.split(//)
    s2_array = s2.split(//)
    len = [s1_array.length, s2_array.length].min
    dist = 0
    return dist unless len > 0
    for i in 0...len do
      dist += 1 if s1_array[i] != s2_array[i]
    end
    dist
  end