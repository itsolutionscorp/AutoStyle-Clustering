class Hamming
  def self.compute (s1, s2)
    counter = 0
    s1.upcase
    s2.upcase
    arr1 = s1.split("")
    arr2 = s2.split("")
    iterate = s1.length > s2.length ? s2.length : s1.length
    iterate.times do |i|
      counter += 1 if arr1[i] != arr2[i]
    end
    return counter
  end
end
