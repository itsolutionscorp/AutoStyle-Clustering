class Acronym
  def self.abbreviate(words)
    words.scan(/[A-Z]+[a-z]*|[a-z]+/).map { |word| word[0...-(word.length-1)] }.join.upcase
  end
end
