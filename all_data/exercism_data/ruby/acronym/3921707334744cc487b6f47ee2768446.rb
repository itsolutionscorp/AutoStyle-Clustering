module Acronym
  def self.abbreviate(string)
    string.split(/[\s]/).map {|word| Word.new(word).to_acronym}.join('')
  end

  class Word
    def initialize(string)
      @string = string
    end

    def to_acronym
      if camelcase?
        capitals.join('')
      elsif hyphenated?
        @string.split(/-/).map { |word| word[0].upcase }
      else
        @string[0].upcase
      end
    end

    def upcase?
      @string == @string.upcase
    end

    def capitals
      @string.scan(/[A-Z]/)
    end

    def camelcase?
      !capitals.empty? && !upcase? 
    end

    def hyphenated?
      @string =~ /-/
    end
  end
end
