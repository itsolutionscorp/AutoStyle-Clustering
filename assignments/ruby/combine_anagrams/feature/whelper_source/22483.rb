def combine_anagrams(words)
  hash = Hash.new
  words.each do |word|
    sorted = word.downcase.split("").sort.join
    hash[sorted] = [] unless hash[sorted]
    hash[sorted].push(word)
  end
  array = []
  hash.each { |key, value| array.push(value) }
  return array
end

