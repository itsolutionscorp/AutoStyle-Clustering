def combine_anagrams(words)
  res = Hash.new(0)
  words.each do |elem|
    idx = elem.downcase.chars.sort.join
    if res.has_key?(idx) then
      list = res[idx]
      (list << elem)
      res[idx] = list
    else
      list = Array.new
      list.push(elem)
      res[idx] = list
    end
  end
  return res.values
end