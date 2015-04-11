class Bob 
  def hey(talking)

    speech_analysis = TeenDialogAnalyzer.new(talking)

    if !speech_analysis.saying_something?
      'Fine. Be that way!'
    elsif speech_analysis.yelling?
      'Woah, chill out!'
    elsif speech_analysis.asking_a_question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class TeenDialogAnalyzer
  def initialize(phrase)
    @phrase = phrase.to_s.strip
  end

  def yelling?
    @phrase.upcase == @phrase
  end

  def saying_something?
    @phrase != ""
  end

  def asking_a_question?
    @phrase.end_with?("?")
  end
end
