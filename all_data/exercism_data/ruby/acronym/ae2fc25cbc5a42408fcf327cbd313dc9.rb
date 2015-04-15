class Acronym
  class << self
    def abbreviate(phrase)
      abbreviation = given_abbreviation(phrase)
      return abbreviation unless abbreviation.nil?

      phrase
        .scan(uppercase_or_preceding_whitespace_or_hyphen)
        .map{|char| char.gsub(non_word_characters, '').upcase }
        .join('')
    end

    def given_abbreviation(phrase)
      phrase
        .scan(word_before_colon)
        .flatten
        .first
    end

    def non_word_characters
      /\W/
    end

    def uppercase_or_preceding_whitespace_or_hyphen
      /[A-Z]|\s\w|-\w/
    end

    def word_before_colon
      /(\w*):/
    end
  end
end
