

def combine_anagrams(words)
  anagrams = Hash.new
  words.each do |w|
    sorted = w.downcase.split('').sort.join
    if (anagrams.has_key?(sorted))
      anagrams[sorted] += [w]
    else
      anagrams[sorted] = [w]
    end
  end
  return anagrams.values
end