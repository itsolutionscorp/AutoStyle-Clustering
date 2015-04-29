class Bob
  Question = -> (message) { message.end_with?('?') }
  Shout    = -> (message) { message == message.upcase && message =~ /[[:alpha:]]/ }
  Silence  = -> (message) { message =~ /\A\s*\z/ }

  # Approach taken from Bozhidar Batsov's recent blog post:
  # http://batsov.com/articles/2013/09/24/lambdas-slash-procs-in-case-expressions/
  def hey(message)
    case message
    when silence?
      'Fine. Be that way!'
    when shout?
      'Woah, chill out!'
    when question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def question?
    Question
  end

  def shout?
    Shout
  end

  def silence?
    Silence
  end
end
