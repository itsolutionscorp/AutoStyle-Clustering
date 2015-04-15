class Bob
  def hey(content)
    sentence = Sentence.new(content)
    case 
    when sentence.silent? then 'Fine. Be that way!'
    when sentence.yelling? then 'Woah, chill out!'
    when sentence.asking? then 'Sure.'
    else 'Whatever.'
    end
  end
end

class Sentence
  def initialize(content)
    @content = content
  end
  
  def silent?
    @content.to_s.strip.empty?
  end

  def yelling?
    @content.upcase == @content
  end

  def asking?
    @content.end_with? '?'
  end
end
