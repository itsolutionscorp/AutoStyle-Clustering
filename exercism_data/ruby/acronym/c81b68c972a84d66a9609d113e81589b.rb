# class to obtain acronym from names
class Acronym
  def self.abbreviate(acronym)
    acronym.split(' ').each_with_object('') do |word, result|
      result << (word[0]).upcase
      word[0] = ''
      result << word.scan(/([A-Z])[a-z]/).join
      result << word.scan(/-([a-z])/).join.upcase
    end
  end
end
