def combine_anagrams(words)
  h = Hash.new(0)
  words.each do |word|
    puts("Processing #{word}")
    key = get_key(word)
    puts("Key=#{key}")
    if h.has_key?(key) then
      puts("exists")
      h[key].push(word)
    else
      puts("non existent")
      a = Array.new
      a.push(word)
      h[key] = a
    end
  end
  b = Array.new
  h.each_value { |value| b.push(value) }
  return b
end