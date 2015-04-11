class Bob

  def hey(sentence)
    sentence = Sentence.new(sentence)
    if sentence.pause?
      'Fine. Be that way!'
    elsif sentence.shouting?
      'Woah, chill out!'
    elsif sentence.question?
      'Sure.'      
    else
      'Whatever.'
    end
  end

end

class Sentence
  
  def initialize(sentence)
    @sentence = sentence
  end

  def pause?
    @sentence.nil? || @sentence.strip == ''
  end

  def shouting?
    @sentence.upcase == @sentence
  end

  def question?
    @sentence.split("").last == "?"
  end
end
