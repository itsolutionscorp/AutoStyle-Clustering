def combine_anagrams(words)
  combined = {}
  words.each do |w|
    cword = w.downcase.chars.sort.join
    combined[cword] ||= []
    combined[cword] << w
  end
  combined.values
end
