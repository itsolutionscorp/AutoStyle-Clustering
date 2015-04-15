class Acronym
  def self.abbreviate(sentence)
     sentence.scan(/[A-Z]+[a-z]*|[a-z]+/).map(&:chr).map(&:upcase).join
  end
end
