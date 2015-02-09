def combine_anagrams(words)
  @anagrams = {}
  words.each do |word|
    sorted = word.downcase.split(//).sort
    if @anagrams.member?(sorted)
      @anagrams[sorted] << word
    else
      @anagrams.store(sorted,[word])
    end
  end
  @anagrams.values
end
