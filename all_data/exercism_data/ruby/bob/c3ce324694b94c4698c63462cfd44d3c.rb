module LackadaisicalTeenager
  def hey(message)
    key = answers.keys.find { |name| method(name).call(message) }
    key && answers[key] || default_answer
  end

  private

  def silent?(message)
    message.strip.length.zero?
  end

  def shouting?(message)
    message.chars.select { |c| c.match(/[A-Za-z]/)  }.map { |c| !!c.match(/[A-Z]/) }.reduce(&:&)
  end

  def question?(message)
    message[-1] == '?'
  end
end

class Bob
  include LackadaisicalTeenager

  private

  def answers
    @answers ||= {
      silent?:    'Fine. Be that way!',
      shouting?:  'Woah, chill out!',
      question?:  'Sure.'
    }
  end

  def default_answer
    'Whatever.'
  end
end
