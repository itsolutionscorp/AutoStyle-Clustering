class Bob
  def hey(string)
    bob_say(Phrase.new(string))
  end

  def bob_say(something)
    if something.yell?
      'Woah, chill out!'
    elsif something.question?
      'Sure.'
    elsif something.silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Phrase
  def initialize(string)
    @string = string
  end

   def yell?
    upcase?(@string) && !is_number?(@string)
   end

  def question?
    @string.end_with?('?')
  end

  def silence?
    @string.strip.empty?
  end
  
  private

  def upcase?(string)
    string == string.upcase
  end

  def is_number?(string)
    true unless string.match(/[a-zA-Z]/)
  end
end
