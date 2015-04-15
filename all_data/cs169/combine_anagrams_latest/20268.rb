def combine_anagrams(words)
  ret = Hash.new
  #ret.default_proc = Array.new
  words.each do |word|
    key = word.downcase.chars.sort
    list = ret[key]
    if list.nil?  
      list = []
      ret[key] = list 
    end
    list << word
  end
  ret.values
end
