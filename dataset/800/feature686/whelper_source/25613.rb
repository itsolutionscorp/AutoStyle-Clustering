def combine_anagrams(words)
  result = []
  len = words.length
  for i in (0..(len - 1)) do
    (compare_word = words[i].downcase.chars.sort.join
    current_arr = []
    words.each do |word|
      buffer = word.downcase.chars.sort.join
      if (buffer.length == compare_word.length) then
        if compare_word.start_with?(buffer) then
          puts(((compare_word + " starts with ") + buffer))
          current_arr.push(word)
        end
      end
    end
    if (current_arr.length > 0) and (result.index(current_arr) == nil) then
      result.push(current_arr)
    end
    puts(current_arr.to_s))
  end
  return result
end

