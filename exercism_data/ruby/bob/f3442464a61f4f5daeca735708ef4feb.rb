class Bob
  def hey( input )
    answer_to Sentence.new input
  end
    
  def answer_to( line )
    if line.isSilence?
      return "Fine. Be that way!"
    elsif line.isYelling?
      return "Woah, chill out!"
    elsif line.isQuestion?
      return "Sure."
    else
      return "Whatever."
    end
  end
end

class Sentence
  def initialize( line )
    @content = line.to_s.strip
  end
    
  def isSilence?
    @content.empty?
  end
      
  def isQuestion?
    @content.end_with? '?'
  end
        
  def isYelling?
    if /[a-z]/i.match @content
      @content == @content.upcase
    else
      false
    end
  end
end
