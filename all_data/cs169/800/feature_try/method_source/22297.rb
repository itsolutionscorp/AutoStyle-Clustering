def combine_anagrams(words)
  groups = Hash.new
  words.each do |original_word|
    word = original_word.downcase
    if (groups[sort_letters(word)] == nil) then
      groups[sort_letters(word)] = [original_word]
    else
      groups[sort_letters(word)].push(original_word)
    end
  end
  final = Array.new
  groups.each { |item| final.push(item[1]) }
  final
end