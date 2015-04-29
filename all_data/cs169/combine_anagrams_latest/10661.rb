def combine_anagrams(words)
  hash = Hash.new
  groups = Array.new
  words.each do |word|
    key = word.downcase.chars.sort { |a, b| a.casecmp(b) }.join
    if !hash.has_key? key
      hash[key] = Array.new << word
    else
      hash[key] << word
    end
  end

  #now with all the keys in my hash with arrays as the values should be able to iterate
  #again and check for anagrams
  hash.each_value {|value| groups << value}
  groups
end