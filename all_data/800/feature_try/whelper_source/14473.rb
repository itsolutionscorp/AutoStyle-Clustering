def combine_anagrams(words)
  a = Hash.new
  res = Array.new
  words.each do |value|
    str = value.downcase.split(//)
    str = str.sort
    str = str.join
    a[str] = Array.new if (a[str] == nil)
    a[str].push(value)
  end
  a.each { |key, value| res.push(value) }
  return res
end

