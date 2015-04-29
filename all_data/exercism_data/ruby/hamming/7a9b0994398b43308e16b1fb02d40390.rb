class Hamming
  def self.compute(string1, string2)
    (0...[string1.length, string2.length].min).count do |n|
      string1[n] != string2[n]
    end
  end
end
