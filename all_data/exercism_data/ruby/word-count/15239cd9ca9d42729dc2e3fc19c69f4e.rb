class Phrase < Struct.new(:phrase)
  WORDS_REGEX = /[a-zA-Z0-9]+/

  def word_count
    dictionary = {}

    words.each do |w|
      dictionary[w] ||= 0
      dictionary[w] += 1
    end

    dictionary
  end

  def words
    phrase.scan(WORDS_REGEX).map(&:downcase)
  end
end
