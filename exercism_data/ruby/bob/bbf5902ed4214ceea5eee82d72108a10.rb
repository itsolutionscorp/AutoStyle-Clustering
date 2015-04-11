class Whatever
  def match(message)
    true
  end

  def execute
    'Whatever.'
  end
end

class Chillout
  def match(message)
    message.upcase == message && message.match(/[a-z]/i)
  end

  def execute
    'Woah, chill out!'
  end
end

class Sure
  def match(message)
    message.end_with? '?'
  end

  def execute
    'Sure.'
  end
end

class Fine
  def match(message)
    message.strip.empty?
  end

  def execute
    'Fine. Be that way!'
  end
end

class Bob
  def possible_anwers
    whatever = Whatever.new
    chillout = Chillout.new
    sure = Sure.new
    fine = Fine.new

    [chillout, sure, fine, whatever]
  end

  def answer_finder(message)
    possible_anwers.find { |x| x.match(message) }
  end

  def hey(message)
    answer_finder(message).execute
  end
end
