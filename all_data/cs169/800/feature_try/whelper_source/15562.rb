def combine_anagrams(input)
  h = Hash.new
  input.each do |word|
    h[canonical_key(word)] = [] if h[canonical_key(word)].nil?
    (h[canonical_key(word)] << word)
  end
  h.map { |key, value| value.sort }
end

def canonical_key(word)
  word.downcase.each_char.sort.join
end

