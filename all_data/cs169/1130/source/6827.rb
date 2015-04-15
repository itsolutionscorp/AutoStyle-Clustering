def combine_anagrams(words)
  result_hash = {}
  result_arr = []
  words.each do |word|
    sorted = word.downcase.chars.sort.join
      if result_hash.has_key?(sorted)
        result_hash[sorted] << word
      else
        result_hash[sorted] = [word]
      end
  end
  result_hash.each do |k, v|
    result_arr << v
  end
  result_arr
end
