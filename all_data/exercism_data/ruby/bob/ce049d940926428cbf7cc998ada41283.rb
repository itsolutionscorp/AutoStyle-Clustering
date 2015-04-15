class Bob

  def hey(what)
    is_something?(what) ? say(what) : say_nothing
  end

  private

  def say(what)
    case
    when what.end_with?(".") then  speak(what)
    when what.end_with?("!") then  shout(what)
    when what.end_with?("?") then  ask(what)
    when what.empty?         then  say_nothing
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
