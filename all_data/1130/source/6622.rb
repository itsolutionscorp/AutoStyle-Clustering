def combine_anagrams(words)
  result = []
  testword = []
  testword << words[0]
  result << testword
  words.delete_at(0)
  words.each do |w|
    result.each do |r|
      if r[0].sort == w.sort
        r << w
      end
    end
    newword = []
    newword << w
    result << newword
  end
  result
end

# result = combine_anagrams(['cars','for','potatoes','racs','four','scar','creams','scream'])
# puts result