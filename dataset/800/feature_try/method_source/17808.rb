def combine_anagrams(words)
  tmp = Hash.new
  words.each do |w|
    s = w.downcase.chars.sort.join
    (tmp[s].class == Array) ? (tmp[s].push(w)) : (tmp[s] = [w])
  end
  ret = Array.new
  tmp.each { |key, value| ret.push(value) }
  return ret
end