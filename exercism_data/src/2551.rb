class Hamming
  class << self
    def compute(s1, s2)
      s1 = s1.chars
      mutations = 0
      s2.chars.each_index do |i|
        return mutations if s1[i] == nil || s2[i] == nil   
        mutations += 1 if s1[i] != s2[i] 
      end
      mutations
    end
  end
end
