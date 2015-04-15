
def combine_anagrams(words)

  return [] if words == []

  final_array = []

  words.each do |word|
    anagrams = []

    words.each do |w|
      anagrams << w if ( word.downcase.gsub(" ", "").split('').sort == w.downcase.gsub(" ", "").split('').sort )
    end

    final_array << anagrams
  end

  final_array.uniq
end
