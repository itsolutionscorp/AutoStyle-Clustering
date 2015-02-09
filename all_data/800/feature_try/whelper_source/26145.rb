def combine_anagrams(words)
  sorted = []
  antagrams = Array.new
  words.each { |word| sorted.push(word.downcase.chars.sort.join) }
  sorted = sorted.uniq
  puts(sorted)
  puts("____")
  sorted.each do |sword|
    temp = []
    words.each do |word|
      temp.push(word) if (word.downcase.chars.sort.join == sword)
    end
    puts(temp)
    antagrams.push(temp)
  end
  antagrams
end

