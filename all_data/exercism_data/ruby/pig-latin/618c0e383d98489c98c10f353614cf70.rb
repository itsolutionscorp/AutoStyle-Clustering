require "delegate"

class Word < SimpleDelegator
  VOWEL_MATCHERS = [/[aeio]/, "yt", "xr"]

  def consonants_moved_to_the_end
    i = index_of_first_vowel
    slice(i, length) + slice(0, i)
  end

  def self.wordlist_from(in_string)
    in_string.downcase.scan(/\w+/).map { |word| self.new(word) }
  end

  private

  def index_of_first_vowel
    vowel_indexes.compact.min
  end
  
  def vowel_indexes
    VOWEL_MATCHERS.map{ |matcher| index(matcher) }.push(index_of_first_u)
  end

  def index_of_first_u
    u_index = index("u")
    qu_index = index("qu")
    u_index if u_index && (!qu_index || u_index < qu_index)
  end
end

module PigLatin
  def self.translate(in_string)
    Word.wordlist_from(in_string).map{ |word|
      word.consonants_moved_to_the_end + "ay"
    }.join " "
  end
end
