def combine_anagrams(words)
  anas = []
  words.each do |word|
    found = anas.find do |ana|
      (ana[0].downcase.split(//).sort == word.downcase.split(//).sort)
    end
    found ? ((found << word)) : ((anas << [word]))
  end
  anas
end

