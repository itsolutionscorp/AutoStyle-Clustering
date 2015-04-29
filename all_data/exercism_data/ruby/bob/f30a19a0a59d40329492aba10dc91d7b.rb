class Bob
  def initialize
    @possible_answers = {
      yell: 'Woah, chill out!',
      question: 'Sure.',
      silence: 'Fine. Be that way!',
      default: 'Whatever.'
    }
  end

  def hey words
    (are_yelling?(words) && answer_to(:yell)) ||
      (are_questioning?(words) && answer_to(:question)) ||
      (are_silence?(words) && answer_to(:silence)) ||
      answer_to(:default)
  end

  def answer_to answer_type
    @possible_answers[answer_type]
  end

  def are_yelling? words
    words == words.upcase && words =~ /[a-zA-Z]/
  end

  def are_questioning? words
    words[-1] == '?'
  end

  def are_silence? words
    words.empty? || words =~ /^\s+$/
  end
end
