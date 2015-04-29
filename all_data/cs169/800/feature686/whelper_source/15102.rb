def combine_anagrams(words)
  freq_dict = Hash.new
  words.each do |word|
    chars = word.downcase.chars.sort
    if freq_dict.has_key?(chars) then
      (freq_dict[chars] << word)
    else
      if freq_dict[chars] = [word] then
        # do nothing
      end
    end
  end
  freq_dict.values
end

