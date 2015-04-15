class Acronym
  def self.abbreviate(sentence)
    output = ""
    sentence.scan(/[A-Z]+[a-z]*|[a-z]+/).each do |item|
      output += item.upcase[0]
    end
    output
  end
end
