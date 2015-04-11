class Hamming
  def self.compute(s, t)
    if s == t
      0
    else
      s_array = s.split("")
      t_array = t.split("")

      matches = 0

      s_array.length.times do |i|
        if s_array[i] != t_array[i]
          matches += 1
        end
      end

      matches
    end
  end
end
