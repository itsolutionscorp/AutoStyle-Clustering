class Acronym
  def self.abbreviate(words)
    words = normalize(words)
    words.map { |e| e[0].upcase }.join
  end

  def self.normalize(w)
    w.scan(/[A-Z]+[a-z]*|[a-z]+/)
  end
end
