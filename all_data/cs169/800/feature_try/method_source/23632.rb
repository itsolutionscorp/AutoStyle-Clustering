def combine_anagrams(words)
  @anagrams = []
  words.each do |w|
    @found = false
    @anagrams.each do |a|
      if a[0].anagram?(w) then
        a.push(w)
        @found = true
      end
    end
    @anagrams.push([w]) if (not @found)
  end
  return @anagrams
end