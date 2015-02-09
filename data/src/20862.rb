def combine_anagrams(words)
  words.each do |each|
    for word in words do
      if (each.chars.sort.join == word.chars.sort.join) then
        return "#{each} is a match"
      end
    end
  end
end