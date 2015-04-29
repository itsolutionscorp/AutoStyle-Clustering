def combine_anagrams(words)
  words_unq = Array.new
  words.each { |word| (words_unq << word.downcase.chars.sort.join) }
  words_result = Array.new { }
  words_unq.uniq.each_with_index do |word, i|
    words.each do |anag|
      if (anag.downcase.chars.sort.join == word) then
        if (words_result[i] == nil) then
          words_result[i] = Array.new
          (words_result[i] << anag)
        else
          (words_result[i] << anag)
        end
      end
    end
  end
  return words_result
end

