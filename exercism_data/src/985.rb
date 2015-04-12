class Hamming
  def compute(s1,s2)
    (0..s1.length).inject(0) do |sum,i|
      sum += 1 unless s1[i] == s2[i]
      sum
    end
  end
end
