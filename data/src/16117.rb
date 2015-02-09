def combine_anagrams(words)
  include(Enumerable)
  @words = words
  @temp
  @wordlist = Hash.new(0)
  @newwordlist
  @words.each do |word|
    @temp = word.chars.sort_by(&:downcase).join
    @wordlist[@temp] += 1
  end
  wordlist = @wordlist
  puts(@wordlist)
end