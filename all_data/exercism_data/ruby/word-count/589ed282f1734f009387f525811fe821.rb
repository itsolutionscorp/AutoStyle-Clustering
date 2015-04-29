class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.inject(Hash.new(0)) { |count, e| count[e] += 1 ; count }
  end

  private

  def words
    # split on space, comma or new line
    remove_empty_characters(
      remove_special_characters(
        normalize(
          @phrase.split(/ |,|\n/)
        )
      )
    )
  end

  def normalize(words_in_work)
    words_in_work.map { |e| e.downcase }
  end

  def remove_special_characters(words_in_work)
    # remove except numbers, alphabet and apostrophes
    words_in_work.map { |e| e.gsub(/[^1-9a-z']/, '') }
  end

  def remove_empty_characters(words_in_work)
    words_in_work.reject(&:empty?)
  end
end
