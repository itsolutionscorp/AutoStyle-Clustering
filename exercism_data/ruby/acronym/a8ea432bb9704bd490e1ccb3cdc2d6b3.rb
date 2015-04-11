class Acronym
  def self.abbreviate(phrase)
    phrase.gsub! /[,:-]/, " "

    if recursive_abbreviation? phrase
      phrase.split.first
    else
      make_abbreviation phrase
    end
  end

  def self.make_abbreviation(phrase)
    phrase.split.map do |word|
      word.capitalize! unless upper_case? word.chars.first

      word.chars.select do |char|
        char if upper_case? char
      end
    end.join
  end

  def self.upper_case?(character)
    /[[:upper:]]/.match character
  end

  def self.recursive_abbreviation?(word)
    upper_case?(word[0]) && upper_case?(word[1])
  end

  private_class_method :upper_case?,
                       :recursive_abbreviation?,
                       :make_abbreviation
end
