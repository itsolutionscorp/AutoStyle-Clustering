class Hamming
  
  def self.compute s1, s2
    gap = 0
    s1, s2 = prepare_args(s1, s2)
    s1.each_with_index do |n, i|
      gap += 1 if s2[i] != n
    end
    gap
  end

  def self.prepare_args s1, s2
    s1 = s1.split("")
    s2 = s2.split("")
    if s1.count < s2.count
      (s2.count - s1.count).times do 
        s2.pop
      end
    elsif s2.count < s1.count
      (s1.count - s2.count).times do
        s1.pop
      end
    end
    return s1, s2
  end

end
