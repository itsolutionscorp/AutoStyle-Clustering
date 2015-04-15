class Bob
  def hey(sentence)
    answers.each do |answer|
      match = answer.adequate_for(sentence)
      return match if match
    end
  end

  private

  def answers
    @answers ||= answer_klasses.map(&:new)
  end

  def answer_klasses
    [
      LoudAnswer,
      QuestionAnswer,
      SilenceAnswer,
      DefaultAnswer
    ]
  end

  class LoudAnswer
    def adequate_for(sentence)
      if sentence.match(/[a-zA-Z]/) && sentence.upcase == sentence
        'Woah, chill out!'
      end
    end
  end

  class QuestionAnswer
    def adequate_for(sentence)
      if sentence.end_with?('?')
        'Sure.'
      end
    end
  end

  class SilenceAnswer
    def adequate_for(sentence)
      if sentence.strip.empty?
        'Fine. Be that way!'
      end
    end
  end

  class DefaultAnswer
    def adequate_for(sentence)
      'Whatever.'
    end
  end

end
