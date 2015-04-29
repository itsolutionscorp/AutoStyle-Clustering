def combine_anagrams(words)
  freq_dict = Hash.new
  words.each do |word|
    chars = word.downcase.chars.sort
    if freq_dict.has_key? chars
      freq_dict[chars] << word
    elsif
      freq_dict[chars] = [word]
    end
  end
  freq_dict.values
end

words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
p combine_anagrams(words)