class Acronym
  def self.abbreviate(text)
    text.split(/[a-z](?=[A-Z])|\s|-/)
        .map! { |e| e[0] }
        .join("").upcase
  end
end
