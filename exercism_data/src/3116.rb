class Hamming
  def compute(s, t)
    s_array = s.split(//)
    t_array = t.split(//)
    index = 0
    new_array = []
    s_array.each do |i| 
      if (i != t_array[index] || nil ) && (t_array[index] != nil)
        new_array << i
      end
      index += 1
    end
    new_array.length
  end
end
