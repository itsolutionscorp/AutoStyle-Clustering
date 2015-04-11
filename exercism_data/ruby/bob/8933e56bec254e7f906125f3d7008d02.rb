class Bob
 def hey text
   statement = Statement.new(text) 
   return 'Fine. Be that way!' if statement.silence?
   return "Woah, chill out!" if statement.yell?
   return "Sure." if statement.question?
   'Whatever.'
 end 
end

class Statement
  def initialize text
    @text = text
    @words = @text.split(" ")
  end

  def question?
    @text[@text.size - 1] == "?" 
  end

  def yell?
    return false if uppercase_words.empty?
    uppercase_words.last.match(/^[A-Za-z%^*@#!$\(*^0-9]+!$/) || ( all_uppercase_words? && !all_words_with_only_numbers? )  
  end

  def silence?
    (0..1).include?(@text.squeeze.length)
  end

  private

  def all_uppercase_words?
    uppercase_words.size == @words.size
  end

  def uppercase_words
    @words.select do |word|
      characters_with_upper_case = word.chars.select{ |char| char == char.upcase }.size 
      characters_with_upper_case == word.size 
    end
  end

  def all_words_with_only_numbers?
    @words.collect {|x| x.match(/[0-9]+,*/)}.compact.size == @words.size
  end
end
