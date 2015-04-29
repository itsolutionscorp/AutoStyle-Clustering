def combine_anagrams(words)
  coll = words.inject({}) do |groups, word|
    key = word.downcase.split("").sort.join("").to_sym
    groups[key.to_sym] ||= []
    (groups[key.to_sym] << word)
    groups
  end
  coll.inject([]) { |ret, c| (ret << c[1]) }
end

