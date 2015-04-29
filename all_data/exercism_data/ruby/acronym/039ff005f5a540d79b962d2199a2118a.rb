class Acronym
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def self.abbreviate(phrase)
    new(phrase).abbreviate
  end

  def abbreviate
    extract_first_char(split).join.upcase
  end

  private

  def split
    # Use any non word character OR small case alphabet with an
    # uppercase immediately proceeding it as a pivot for splits.
    @phrase.split(/\W|[a-z](?=[A-Z]{1})/)
  end

  def extract_first_char(list_of_words)
    list_of_words.map { |word| word[0] }
  end
end
