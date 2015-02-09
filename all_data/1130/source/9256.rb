def combine_anagrams(words)
  hash = Hash.new
  words.each do |word|
    key = word.upcase.chars.sort.join
    if hash.has_key? key then
      hash[key] << word
    else
      hash[key] = [word]
    end
  end
  array = Array.new
  hash.each_value { |value| array << value }
  array
end
