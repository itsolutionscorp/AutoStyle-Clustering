def combine_anagrams(words)
  hash = Hash.new
  words.each do |w|
    chars = w.downcase.chars.sort
    if hash.has_key? chars
      hash[chars] << w
    elsif
      hash[chars] = [w]
    end
  end
  hash.values
end