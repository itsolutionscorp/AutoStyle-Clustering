class Acronym
  def self.abbreviate(phrase)
    phrase.gsub(/([a-z\d])([A-Z])/, '\1_\2')
      .split(/[-_ ]/).map { |x| x[0] }.join.upcase
  end
end
