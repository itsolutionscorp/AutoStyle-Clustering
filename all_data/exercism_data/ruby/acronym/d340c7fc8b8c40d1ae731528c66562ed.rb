class Acronym
  def self.abbreviate(term)
    words = term.split(/\s|(?=[A-Z][a-z])|\-/)
    words.reduce("") { |abbr, word| abbr + word.chars.first.upcase }
  end
end
