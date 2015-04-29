class Bob
  def initialize
    @checks = [EmptyCheck, ShoutingCheck, QuestionCheck]
  end

  def hey(sentence)
    @check_chain = @checks.reverse.inject(TerminalCheck.new) { |acc, klass| klass.new(acc) }
    @check_chain.respond_or_pass_on(sentence)
  end

  #Adds a check at the end of the chain
  def <<(check)
    @checks << check
  end

  #Adds a check at the beginning of the chain
  def unshift(check)
    @checks.unshift(check)
  end
end

class Check
  def initialize(next_check)
    @next_check = next_check
  end

  def respond_or_pass_on(sentence)
    if condition(sentence)
      response
    else
      @next_check.respond_or_pass_on(sentence)
    end
  end
end

class EmptyCheck < Check
  def condition(sentence)
    sentence.nil? || sentence.strip == ""
  end

  def response
    "Fine. Be that way!"
  end
end

class ShoutingCheck < Check
  def condition(sentence)
    sentence.upcase == sentence
  end

  def response
    "Woah, chill out!"
  end
end

class QuestionCheck < Check
  def condition(sentence)
    sentence.end_with?("?")
  end

  def response
    "Sure."
  end
end

class TerminalCheck
  def respond_or_pass_on(sentence)
    "Whatever."
  end
end
