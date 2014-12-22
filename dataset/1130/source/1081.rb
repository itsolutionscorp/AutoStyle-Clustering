def combine_anagrams(words)
  anagrams = Hash.new { |hash, key| hash[key] = Array.new }
  letters  = Array.new
  words.each do |word|
    word.each_char do |c|
      letters << c.downcase
    end
    letters.sort!
    hash = letters.join
    anagrams[hash] << word
    letters = []
  end
  anagrams.values
end