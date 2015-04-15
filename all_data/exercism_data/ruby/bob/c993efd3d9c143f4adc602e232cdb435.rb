class Bob
  def hey(phrase)
    dialogue = Dialogue.new(phrase)
    case true
    when dialogue.silence?
      'Fine. Be that way!'
    when dialogue.shouting?
      'Woah, chill out!'
    when dialogue.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  class Dialogue < String
    def alpha_content
      self.gsub(/[^a-z]/i, '')
    end

    def question?
      end_with?('?')
    end

    def silence?
      # sooo tempted to name this 'crickets?'
      self.to_s.strip.empty?
    end

    def statement?
      end_with?('.')
    end

    # alpha chars exist and are upper case
    def shouting?
      !alpha_content.empty? && alpha_content == alpha_content.upcase
    end
  end
end
