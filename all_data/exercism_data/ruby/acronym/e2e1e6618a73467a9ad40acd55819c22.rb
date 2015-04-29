class Acronym
  def self.abbreviate(string)
    string.split.reduce("") do |acronym, word|
      return word.slice(0..-2) if word.end_with?(":")

      if word.match(/-/)
        first, _, second = word.partition(/-/)
        acronym += "#{first[0]}#{second[0]}"
      else
        acronym += word.scan(/^[A-Za-z]|[A-Z]+/).join
      end
    end.upcase
  end
end
