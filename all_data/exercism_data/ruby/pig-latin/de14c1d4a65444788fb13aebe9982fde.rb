class PigLatin
  VOWELS = %w[a e i o xr yt]

  def self.translate(words)
    words = words.split(" ")
    words.map { |word|
      middle, beginning = first_vowel(word)
      beginning + middle + "ay"
    }.join(" ")
  end

  def self.first_vowel(word)
    i = 0
    while i < word.length
      if partition_point(word, i)
        return word[0...i], word[i..-1]
      end
      i += 1
    end
  end

  def self.partition_point(word, i)
    VOWELS.any? { |vowel|
      word[i..-1].start_with?(vowel) ||
      word[(i - 2)...i] == "qu" ||
      word[i - 1] != "q" && word[i] == "u"
    }
  end
end
