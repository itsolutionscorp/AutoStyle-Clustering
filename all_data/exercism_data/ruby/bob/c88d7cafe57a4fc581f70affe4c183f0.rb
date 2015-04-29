class Bob
  def hey str, answers=[Shouting, Silent, Asking, Whatever]
    answers.find { |answer| answer === str }.response
  end
end

module Answer
  def answers(params={})
    @pattern = params.fetch(:pattern)
    @answer  = params.fetch(:answer)
  end

  def ===(message)
    @pattern.call(message.strip)
  end

  def response
    @answer
  end
end

class Whatever
  extend Answer
  answers pattern: ->(*) { true }, answer: "Whatever."
end

class Shouting
  extend Answer
  answers pattern: ->(s) { s =~ /[A-Z]/ && s.upcase == s },
          answer: "Woah, chill out!"
end

class Asking
  extend Answer
  answers pattern: ->(s) { s.end_with?('?') }, answer: "Sure."
end

class Silent
  extend Answer
  answers pattern: ->(s) { s.empty? }, answer: "Fine. Be that way!"
end
