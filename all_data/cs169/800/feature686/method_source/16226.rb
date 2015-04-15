def combine_anagrams(words)
  groups = Hash.new
  words.each do |word|
    sorted = word.chars.sort.join.downcase
    if groups.has_key?(sorted) then
      groups[sorted] = (groups[sorted] << word)
    else
      groups[sorted] = [word]
    end
  end
  groups.values
end