class Bob
  def hey(text)
    @text = text

    if nothing?
      responds[:nothing]
    elsif question?
      responds[:question]
    elsif yell?
      responds[:yell]
    else
      responds[:else]
    end
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

  def nothing?
    @text.nil? or @text.match(/\A\s*\z/)
  end

  def question?
    @text.end_with?('?')
  end

  def yell?
    @text.match(/[[:upper:]]/) and not @text.match(/[[:lower:]]/)
  end
end
