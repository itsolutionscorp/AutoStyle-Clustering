def anagram?(word1, word2)
  word1.chars.sort.join == word2.chars.sort.join
end

def combine_anagrams(words)
  anagrams = []
  words.each do |i|
      added_already = false
      anagrams.each do |j|
        if anagram?(j[0].downcase, i.downcase) then j << i; added_already = true; break; end;
      end
      anagrams.push [i] unless added_already
  end
  anagrams
end



test = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
puts "#{combine_anagrams test}"