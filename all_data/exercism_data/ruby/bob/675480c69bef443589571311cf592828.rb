class Bob
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
    @question ||= -> (message) { message.end_with?('?') }
  end

  def shout?
    @shout ||= -> (message) { message == message.upcase && message =~ /[[:alpha:]]/ }
  end

  def silence?
    @silence ||= -> (message) { message =~ /\A\s*\z/ }
  end
end
