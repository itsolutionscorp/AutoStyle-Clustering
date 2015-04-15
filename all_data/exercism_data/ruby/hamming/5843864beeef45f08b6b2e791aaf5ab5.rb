class Hamming
  class << self
    
    def compute(s1,s2)
      if s1.length == s2.length
        hamming_count=0
        0.upto(s1.length - 1) do |n|
          hamming_count += 1 if s1[n] != s2[n]
        end
        return hamming_count
      else
        return "N/A"
      end
    end
  end
end
