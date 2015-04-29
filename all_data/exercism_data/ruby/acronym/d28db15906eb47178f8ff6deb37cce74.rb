module Acronym
  def self.abbreviate(phrase)
    phrase.scan(/[A-Z](?=[a-z])|\b\w/).join.upcase
  end
end
