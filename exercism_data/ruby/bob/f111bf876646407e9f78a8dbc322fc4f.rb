class Bob
  def hey(phrase)
    dialogue = Dialogue.new(phrase)
    case true
    when dialogue.is_silence?
      'Fine. Be that way!'
    when dialogue.is_shouting?
      'Woah, chill out!'
    when dialogue.is_question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  class Dialogue < String
    def alphanumeric_content
      self.delete(",.?! ")
    end

    def is_only_numbers?
      is_number?(alphanumeric_content)
    end

    def is_question?
      end_with?('?')
    end

    def is_silence?
      # sooo tempted to name this 'is_crickets?'
      self.to_s.strip == ''
    end

    def is_statement?
      end_with?('.')
    end

    def is_shouting?
      return false unless self == self.upcase
      return false if is_only_numbers?
      return !is_statement?
    end

    private

    def is_number?(value)
      value.to_f.to_s == value.to_s || value.to_i.to_s == value.to_s
    end
  end
end
