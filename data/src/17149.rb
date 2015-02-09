def combine_anagrams(words)
  sorted = []
  words.each do |word|
    inserted = false
    sorted.each do |sort|
      if (sort[0].downcase.chars.sort.join == word.downcase.chars.sort.join) then
        (sort << word)
        inserted = true
      end
    end
    (sorted << [word]) unless inserted
  end
  sorted
end