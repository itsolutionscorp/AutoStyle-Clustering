module Acronym
  def self.abbreviate(text)
    intro = text[0..2].scan(/[A-Z]/)
    return intro.join if intro.length == 3

    text
      .split(/\s|-/)
      .map(&method(:extract_letters))
      .map(&:upcase)
      .join
  end

  private

  def self.extract_letters(word)
    (word.scan(/[A-Z]/)[1..-1] || []).unshift(word[0]).join
  end
end
