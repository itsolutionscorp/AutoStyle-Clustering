class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words_list = cleanup @phrase

    Hash.new.tap do |result|
      words_list.uniq.each do |word|
        result[word] = words_list.count(word)
      end
    end
  end

  private
  def clean_punctuation(string)
    string.gsub(/[^a-zA-Z  0-9]/, ' ')
  end

  def normalize_to_ary(string)
    string.split(' ').map(&:downcase)
  end

  def cleanup(string)
    normalize_to_ary( clean_punctuation( string ) )
  end

end
