class Phrase

  WORD_REGEX = /[^\w\d\']+/

  def initialize phrase_string
    @string = phrase_string
  end

  def word_count
    @counts ||= string_to_word_count @string
  end

  private

    def string_to_word_count string
      Hash.new(0).tap do |counts|
        string.downcase.split(WORD_REGEX).map { |word| counts[word] += 1 }
      end
    end
end
