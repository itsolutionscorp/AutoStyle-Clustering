def combine_anagrams(words)
  hash = Hash.new([])
  words.each do |word|
    hash[word.ordered] += [word]
  end
  hash.values
end

class String
  def ordered
    self.downcase.split('').sort.join
  end
end
