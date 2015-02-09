def combine_anagrams(words)
  anagram = []
  return [] if words == []
  anagram.push([words[0]])
  words.delete_at(0)

  words.each do |word|
    flag = 0
    anagram.each do |ana|
      if word.downcase.split("").sort == ana[0].downcase.split("").sort
        ana.push(word)
        # words.delete(word)
        flag = 0
        break
      else
        flag = 1
      end
    end
    anagram.push([word]) if flag == 1
  end
  anagram
end

if __FILE__ == $0
  input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
  #  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
  # ["creams", "scream"]]
  puts combine_anagrams(input)
end