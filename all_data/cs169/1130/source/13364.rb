def combine_anagrams(words)
  groups_hash = {}
  words.each do |word|
    key = word.downcase.scan(/./).sort.join
    old = groups_hash[key]  
    if(old.nil?)
      old = []
    end
    new_array = old << word
    groups_hash[key] = new_array
  end
  groups_hash.values
end