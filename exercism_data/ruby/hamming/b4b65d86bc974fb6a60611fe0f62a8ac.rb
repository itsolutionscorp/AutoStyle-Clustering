class Hamming
  def self.compute(s1, s2)
    return 0 if s1.length != s2.length
    chars1 = s1.split(//)
    chars2 = s2.split(//)
    filtered = chars1.zip(chars2).select { |arr|
     arr[0] != arr[1]
    } 
    filtered.length
  end
end
