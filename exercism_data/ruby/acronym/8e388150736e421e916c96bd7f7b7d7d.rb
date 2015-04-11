class Acronym
  def self.abbreviate(phrase)
    "".tap do |acronym|
      each_word(phrase) do |word|
        acronym << word[0].upcase
      end
    end
  end

  def self.each_word(phrase, &block)
    phrase.scan(/[A-Z]+[a-z]*|[a-z]+/, &block)
  end
end
