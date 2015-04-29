class Acronym

  def self.abbreviate(phrase)
    phrase.scan(/[A-Z]+[a-z]*|[a-z]+/).map(&:chr).join.upcase
  end

end
