module PigLatin
  def self.translate(phrase)
    phrase.gsub(/[[:word:]]+/) do |word|
      word.gsub(/\A((?:qu|[xy](?=[aeiou])|[^aeiouxy])*)(.*)/, '\2\1ay')
    end
  end
end
