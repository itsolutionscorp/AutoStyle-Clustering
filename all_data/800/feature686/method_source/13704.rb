def combine_anagrams(words)
  ans = {}
  words.each do |word|
    a = word.downcase.chars.sort.join
    ans[a] = [] if ans[a].nil?
    (ans[a] << word)
  end
  vals = []
  ans.each_value { |val| (vals << val) }
  vals
end