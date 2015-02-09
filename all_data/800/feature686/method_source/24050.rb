def combine_anagrams(words)
  hash = {}
  words.each do |word|
    key = word.downcase.split(//).sort
    hash[key] = ((hash[key] or []) + [word])
  end
  ans = []
  hash.map { |k, v| ans = (ans + v) }
  ans
end