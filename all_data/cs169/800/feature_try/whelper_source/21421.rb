def combine_anagrams(words)
  hash = Hash.new
  words.each do |word|
    sorted = word.downcase.split(//).sort.join
    printf(sorted)
    if hash.has_key?(sorted) then
      hash[sorted] = (hash[sorted] << word)
    else
      newarray = []
      hash[sorted] = (newarray << word)
    end
  end
  returnarray = []
  hash.each { |key, value| (returnarray << value) }
  returnarray
end

