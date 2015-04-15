class Acronym
  def self.abbreviate(phrase)
    phrase.gsub("-", " ")
          .split(' ')
          .map { |p| p.upcase == p ? p.chars.first : p }
          .flat_map { |a| a.split(/(?=[A-Z])|[\s,]+/) }
          .collect { |p| p.chars.first }
          .join
          .upcase
  end
end
