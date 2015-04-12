class Hamming
  def compute (s1, s2)
    counter = 0
    s1.upcase
    s2.upcase
    arr1 = s1.scan /\w/
    arr2 = s2.scan /\w/
    iterate = s1.length > s2.length ? s2.length : s1.length
    iterate.times do |i|
      counter += arr1[i] == arr2[i] ? 0 : 1
    end
    return counter
  end
end
