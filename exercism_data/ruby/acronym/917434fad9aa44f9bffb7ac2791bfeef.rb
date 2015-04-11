class Acronym
  def self.abbreviate(phrase)
    new(phrase).abbreviate
  end

  def initialize(raw_phrase)
    @phrase = sanitize(raw_phrase)
  end

  def abbreviate
    return existing_acronym if existing_acronym.present?

    phrase.split(" ").map { |word|
      capitals = extract_capitals(word)
      capitals.present? ? capitals : word[0]
    }.join.upcase
  end

  private

  attr_reader :phrase

  def sanitize(raw_phrase)
    raw_phrase.gsub("-", " ")
  end

  def existing_acronym
    phrase.scan(/^[A-Z]{2,}/).join
  end

  def extract_capitals(word)
    word.scan(/[A-Z]/).join
  end
end

class String
  def present?
    !empty?
  end
end
