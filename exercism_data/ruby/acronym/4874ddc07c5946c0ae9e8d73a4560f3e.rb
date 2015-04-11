class Acronym
  def self.abbreviate(text)
    words = text.split(/[a-z](?=[A-Z])|\s|-/)
    words.map! { |e| e[0] }
    words.join("").upcase
  end
end
