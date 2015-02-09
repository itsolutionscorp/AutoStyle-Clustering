def combine_anagrams(words)
  map = Hash.new
  if words != nil
    words.each do |word|
      k = word == nil ? nil : word.chars.sort { |a, b| a.casecmp(b) }.join
      map[k] ||= []
      map[k] << word
    end
  end
  return map.values
end

