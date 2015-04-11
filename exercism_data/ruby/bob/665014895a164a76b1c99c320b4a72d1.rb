###
# This implementation represents the present state of teenager-Bob
# while allowing room for eventual maturation into adult-Bob
#
# The concepts of Yelling, Question, Silence and Unclassified Input are
# external to and independent of Bob's personal implementation
#
# The classification of text as a given type of input is entirely
# dependent upon Bob's current implementation and subject to change
# at any time
###

class Bob
  def hey(text)
    input = classify(text)

    react_to input
  end

  private

  def classify(text)
    text.gsub!(/\n/, '')

    case
    when yelling?(text)
      Input::Yelling.new
    when question?(text)
      Input::Question.new
    when silence?(text)
      Input::Silence.new
    else
      Input::Unclassified.new
    end
  end

  def yelling?(text)
    text =~ /[A-Z]/ &&
    text !~ /[a-z]/
  end

  def question?(text)
    text[-1, 1] == '?'
  end

  def silence?(text)
    text =~ /^\s*$/
  end

  def react_to(input)
    case input
    when Input::Question
      'Sure.'
    when Input::Yelling
      'Woah, chill out!'
    when Input::Silence
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Input
  class Question < Input; end

  class Yelling < Input; end

  class Silence < Input; end

  class Unclassified < Input; end
end
