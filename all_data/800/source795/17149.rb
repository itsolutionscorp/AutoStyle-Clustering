def combine_anagrams(words)
  sorted = []
  words.each do |word|
    inserted = false
    sorted.each do |sort|
      if sort[0].downcase.chars.sort.join == word.downcase.chars.sort.join
        sort << word
        inserted = true
      end
    end
    unless inserted
     sorted << [word]
    end
  end
  sorted
end