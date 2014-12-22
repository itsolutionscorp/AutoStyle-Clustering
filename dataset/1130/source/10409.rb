def combine_anagrams(words)
  hash = words.reduce({}) do |hash, value|
    sorted_letters = value.downcase.split('').sort.join
    hash[sorted_letters] = [] unless hash.has_key? sorted_letters
    hash[sorted_letters] << value
    hash
  end
  hash.values
end