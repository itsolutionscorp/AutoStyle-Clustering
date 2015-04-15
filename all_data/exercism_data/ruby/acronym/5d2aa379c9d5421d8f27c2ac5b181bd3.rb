class Acronym
  def self.abbreviate(phrase)
    phrase.split(/[ -]|(?:[a-z](?=[A-Z]))/).map(&:chr).map(&:upcase).join
  end
end
