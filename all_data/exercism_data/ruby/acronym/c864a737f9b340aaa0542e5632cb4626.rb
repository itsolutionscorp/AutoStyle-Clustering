class Acronym
  def self.abbreviate(name)
    name.split.map{ |word| word.split('-').map { |word2| word2[0].capitalize }}.join
  end
end
