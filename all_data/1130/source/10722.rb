def combine_anagrams(words)
  h = Hash.new
  words.each do |word|
    s = word.downcase.scan(/./).sort.inject {|x, y| x + y}
    if h.has_key?(s) then
      h[s] << word
    else
      h[s] = Array[word]
    end
  end
  a = Array.new
  h.each_value do |v|
    a << v 
  end
  return a
end

