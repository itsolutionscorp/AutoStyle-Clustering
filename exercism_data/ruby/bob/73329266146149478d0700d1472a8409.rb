class Bob
  def hey(content)
    sentence = Sentence.new(content)
    
    if sentence.empty?
      'Fine. Be that way!'
    elsif sentence.yelling?
      'Woah, chill out!'
    elsif sentence.asking?
      'Sure.'
    else
      'Whatever.'
    end
  end
  
end

class Sentence
  @sentence
  
  def initialize(sentence)
    @sentence = sentence
  end
  
  def empty?
    @sentence.nil? || @sentence.strip.empty?
  end

  def yelling?
    @sentence.upcase == @sentence
  end

  def asking?
    @sentence.end_with? '?'
  end

end
