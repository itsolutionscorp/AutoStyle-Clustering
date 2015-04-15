def combine_anagrams(words)
  hash = Hash.new
  hash.default = []
  words.each do |s|
    q = s.downcase.split("").sort { |a, b| (a <=> b) }.join("")
    hash[q] = (hash[q] + [s])
  end
  res = Array.new
  hash.each { |k, v| res = (res + [v]) }
  res
end

