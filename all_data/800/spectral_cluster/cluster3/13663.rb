def combine_anagrams(words)
  hash = Hash.new
  words.each do |word|
    key = word.downcase.chars.sort.join
    if(hash.key? key)
    hash[key] << word
    else
      hash[key] = [word]
    end
  end

  array = Array.new
  hash.each do |k, v|
    array << v;
  end
  return array;
end
