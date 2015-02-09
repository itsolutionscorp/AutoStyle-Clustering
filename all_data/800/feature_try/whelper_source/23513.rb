def combine_anagrams(words)
  words = ["cars", "for", "potatoes", "racs", "four", "scar", "creams", "scream"]
  anas = Hash.new
  words.each do |word|
    key = word.split(//).sort
    if anas.has_key?(key) then
      anas[key].push(word)
    else
      anas[key] = Array.new(1, word)
    end
  end
  anas.values
end

