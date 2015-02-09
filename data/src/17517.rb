def combine_anagrams(words)
  return words if (words == nil)
  return words if (words.length == 0)
  swords = words.map { |w| [w.downcase.split(//).sort.join, w] }
  owords = swords.sort
  road = owords.map { |a, b| a }.uniq
  ret = []
  temp = []
  prev = owords[0][0]
  owords.each do |a, b|
    if prev.eql?(a) then
      temp.push(b)
    else
      ret.push(temp.sort)
      temp = []
      temp.push(b)
    end
    prev = a
  end
  ret.push(temp)
  return ret
end