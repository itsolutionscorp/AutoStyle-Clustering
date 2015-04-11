class Acronym
  class << self

    def abbreviate(phrase)
      phrase_before_delim = text_before(phrase)
      phrase_array = split_phrase_on(phrase_before_delim, / |(?=[A-Z])|-/)
      produce_abbreviation(phrase_array)
    end

    private
      def text_before(phrase, delimeter = ':')
        phrase.split(delimeter)[0]
      end

      def split_phrase_on(phrase, regexp)
        phrase.split(regexp)
      end

      def produce_abbreviation(array)
        array.inject('') { |output, word| output + word[0].upcase }
      end

  end
end
