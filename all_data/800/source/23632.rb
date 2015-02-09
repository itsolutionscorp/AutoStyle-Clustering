class String
  def anagram?(string)
    return self.downcase.split(//).sort == string.downcase.split(//).sort
  end
end

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
    if !@found then
      @anagrams.push([w])
    end
  end
  return @anagrams
end

