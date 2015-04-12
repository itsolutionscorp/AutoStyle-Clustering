class Hamming
  def compute(s1,s2)
    (0..s1.length).inject(0) do |sum,i|
      s1[i] == s2[i] ? sum : sum + 1
    end
  end
end
