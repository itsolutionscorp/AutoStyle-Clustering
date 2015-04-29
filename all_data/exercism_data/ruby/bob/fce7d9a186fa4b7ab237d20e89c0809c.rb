# encoding: utf-8

# implementation of Bob for exercism exercise
class Bob
  def hey(sentence)
    case
    when silence?(sentence)
      'Fine. Be that way!'
    when shout?(sentence)
      'Woah, chill out!'
    when question?(sentence)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silence?(sentence)
    sentence.strip.empty?
  end

  def question?(sentence)
    sentence[-1] == '?'
  end

  def shout?(sentence)
    # apparently Bob knows if you spoke only in letters
    alphas = sentence.tr('^A-Za-z', '')
    !alphas.empty? && (alphas.upcase == alphas)
  end
end
