def combine_anagrams(words)
  uniqe_words = words.map { |word| word.downcase.split('').sort.join }.uniq
  result = Array.new
  uniqe_words.each { |pattern|
    tab = Array.new
    words.each { |word|
      if pattern == word.downcase.split('').sort.join
        tab << word
      end
    }
    result << tab
  }
  result
end

print combine_anagrams( ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams',
'scream'])