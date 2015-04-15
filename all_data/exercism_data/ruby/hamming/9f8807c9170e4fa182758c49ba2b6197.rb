class Hamming

  def self.compute(s1, s2)
    s1, s2 = equalize(s1, s2)

    diff=0
    s1.each_char.each_with_index do |c, i|
      diff+=1 unless c==s2[i]
    end
    diff
  end

  private
  def self.equalize(s1, s2)
    min_length = [s1.length, s2.length].min
    s1 = s1[0..min_length-1]
    s2 = s2[0..min_length-1]
    [s1, s2]
  end

end
