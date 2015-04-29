class Bob

  def hey(what)
    is_something?(what) ? say(what, punctuation = what[-1]) : say_nothing
  end

  private

  def say(what, punctuation)
    case punctuation
    when "." then  speak(what)
    when "!" then  shout(what)
    when "?" then  ask(what)
    when nil then 'Fine. Be that way.'
    else !what.upcase! ? "Woah, chill out!" : nil
    end
  end

  def speak(what)
     got_question_mark?(what) ? 'Whatever.' : 'Whatever.'
  end

  def ask(what)
    'Sure.'
  end

  def shout(what)
    what.upcase! ? 'Whatever.' : 'Woah, chill out!' if got_numbers?(what) || got_special_chars?(what)
  end

  def got_special_chars?(what); what =~ /\W/; end
  def got_numbers?(what); what =~ /\d/; end
  def got_question_mark?(what); what =~ /\?/; end
  def is_something?(meaningful); meaningful != nil; end
  def say_nothing; 'Fine. Be that way.'; end

end
