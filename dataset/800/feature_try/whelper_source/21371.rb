def combine_anagrams(words)
  result = Array.new
  copy_words = words
  words.each do |x|
    tmp_result = Array.new
    copy_words.each do |y|
      if (x.downcase.chars.sort.join == y.downcase.chars.sort.join) then
        (tmp_result << y) if (result.flatten.include?(y) == false)
      end
    end
    (result << tmp_result) if (tmp_result.size > 0)
  end
  result
end

