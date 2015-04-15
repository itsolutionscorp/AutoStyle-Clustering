class Hamming
  def self.compute(string_1, string_2)
    @@min_length = get_loop_length(string_1, string_2)
    calc_distance(string_1, string_2)
  end

  def self.get_loop_length(s1, s2)
    min_length = 0
    if s1.length <= s2.length
      min_length = s1.length
    else
      min_length = s2.length
    end
    min_length-1
  end

  def self.calc_distance(s1, s2)
    number_differences = 0
    for i in 0..@@min_length do
      if s1[i] != s2[i]
        number_differences += 1
      end
    end
    number_differences
  end
end
