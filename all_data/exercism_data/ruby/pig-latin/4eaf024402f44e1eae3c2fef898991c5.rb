module PigLatin
  def self.translate(phrase)
    phrase.gsub(/[[:word:]]+/) do |word|
      word.match(/\A((?:qu|[xy](?=[aeiou])|[^aeiouxy])*)(.*)/) do |m|
        "#{m[2]}#{m[1]}ay"
      end
    end
  end
end
