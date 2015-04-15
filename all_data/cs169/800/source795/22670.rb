def get_anagrams(word, words)
  words.each do |str|
    if word == str.downcase.chars.sort.join
    yield str
    end
  end
end

def combine_anagrams(words)
  anagrams = Array.new
  while words.length > 0
    word = words.shift
    words.delete(word)
    each_anagrams = [word]
    get_anagrams(each_anagrams[0].downcase.chars.sort.join, words){
    |str|
      each_anagrams << str
      words.delete(str)
    }
    anagrams << each_anagrams
  end
  anagrams
end