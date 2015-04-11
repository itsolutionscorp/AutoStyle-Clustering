class Bob 
  def hey (talking)

    @speechAnalysis = TeenDialogAnalyzer.new(talking)

    if @speechAnalysis.IsNotSayingAnything 
      'Fine. Be that way!'
    elsif @speechAnalysis.IsYelling
      'Woah, chill out!'
    elsif @speechAnalysis.AskingAQuestion
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class TeenDialogAnalyzer
  @phrase
  
  def initialize(phrase)
    @phrase = phrase
  end

  def IsYelling
    if @phrase.upcase == @phrase
      true
    else
      false
    end
  end

  def IsNotSayingAnything
    if @phrase.to_s.strip == ""
      true
    else
      false
    end
  end

  def AskingAQuestion
    if @phrase.end_with?("?")
      true
    else
      false
    end
  end
end
