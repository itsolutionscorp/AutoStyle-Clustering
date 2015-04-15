class Bob 
  def hey(talking)

    speech_analysis = TeenDialogAnalyzer.new(talking)

    if speech_analysis.is_not_saying_anything?
      'Fine. Be that way!'
    elsif speech_analysis.is_yelling?
      'Woah, chill out!'
    elsif speech_analysis.is_asking_a_question?
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

  def is_yelling?
    @phrase.upcase == @phrase
  end

  def is_not_saying_anything?
    @phrase == ""
  end

  def is_asking_a_question?
    @phrase.end_with?("?")
  end
end
