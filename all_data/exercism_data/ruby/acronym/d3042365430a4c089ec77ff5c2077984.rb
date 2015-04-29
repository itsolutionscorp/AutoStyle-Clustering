class Acronym
  class << self
    def abbreviate(long_name)
      words = long_name.split(/[\s-]/)
      words.map { |word| initials(word) }.join
    end

    def initials(word)
      if word.upcase == word
        word[0]
      else
        word[0].upcase + word[1...word.length].gsub(/[^A-Z]/, '')
      end
    end
  end
end
