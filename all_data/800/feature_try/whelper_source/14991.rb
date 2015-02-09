def create_anagram_hash(words)
  hash = Hash.new
  words.map do |word|
    added = false
    hash.keys.each do |key|
      if are_anagrams?(word, key) then
        (hash[key] << word)
        added = true
        break
      end
    end
    hash[word] = [word] unless added
  end
  hash
end

def combine_anagrams(words)
  array = []
  create_anagram_hash(words).each_pair { |key, value| (array << value) }
  array
end

