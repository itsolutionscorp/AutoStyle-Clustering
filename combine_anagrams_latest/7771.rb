class AnagramFinder
  def self.group_anagrams(string_array)
    string_array.group_by{|string| letters_of(string)}.values
  end

  def self.anagram?(string1, string2)
    letters_of(string1) == letters_of(string2)
  end

  def self.letters_of(string)
    string.downcase.chars.sort
  end
end

def combine_anagrams(words)
  AnagramFinder.group_anagrams(words)
end
