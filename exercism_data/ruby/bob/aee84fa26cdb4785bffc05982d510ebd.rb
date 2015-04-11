class Bob
  def hey(text)
    teenager_answer(text)
  end


  private

  def responds
    {
      question: 'Sure.',
       nothing: 'Fine. Be that way!',
          yell: 'Woah, chill out!',
          else: 'Whatever.'
    }
  end

  def teenager_answer(text)
    if nothing?(text)
      responds[:nothing]
    elsif question?(text)
      responds[:question]
    elsif yell?(text)
      responds[:yell]
    else
      responds[:else]
    end
  end

  def nothing?(text)
    text.nil? or text.match(/\A\s+\z/)
  end

  def question?(text)
    text.end_with?('?')
  end

  def yell?(text)
    text.match(/[:upper:]/) and not text.match(/[:lower:]/)
  end
end
