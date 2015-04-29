class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(phrase)
    phrase.select do |segment|
      if segment.downcase == word.downcase
        next 
      else
        formatted_statement(segment) == formatted_statement(word)
      end
    end
  end

  def formatted_statement(statement)
    statement.downcase.chars.sort.join
  end

end
