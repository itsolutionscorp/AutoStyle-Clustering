def combine_anagrams(words)
  res = Array.new
  words.each do |word|
    ary = Array.new
    ary << word
    words.each do |word2|
        if word2.downcase.chars.to_a.sort == word.downcase.chars.to_a.sort then
          ary << word2 unless ary.include?(word2)
        end
    end
    res << ary
  end
  result = res.map!{|x| x.sort}.uniq
  return result
end


