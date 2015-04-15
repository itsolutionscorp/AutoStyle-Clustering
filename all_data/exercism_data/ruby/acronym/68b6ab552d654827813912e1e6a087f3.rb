class Acronym

  def self.abbreviate(phrase)
    create_acronym(custom_split(phrase))
  end

  def self.custom_split(phrase)
    phrase.split(/(?=[A-Z])|(?=:)|\s|-/)
  end

  def self.create_acronym(split_phrase, acronym = "")
    split_phrase.each do |word|
      break if word == ":"
      acronym << word[0].upcase
    end
    acronym
  end

end
