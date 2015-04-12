class Hamming
  def compute(s, t)
      cnt = 0
      for i in 0...s.length
        cnt+=1 if s[i] != t[i]
      end
      cnt
  end
end
