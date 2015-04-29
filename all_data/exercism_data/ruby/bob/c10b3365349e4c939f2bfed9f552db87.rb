class Bob
  def hey(phrase_for_bob)
    parse phrase_for_bob
  end

  private
    def parse(phrase_for_bob)
      case
      when phrase_is_empty_or_blank?(phrase_for_bob)
        'Fine. Be that way!'
      when phrase_is_all_caps?(phrase_for_bob)
        'Woah, chill out!'
      when phrase_is_a_question?(phrase_for_bob)
        'Sure.'
      else
        'Whatever.'
      end
    end

    def phrase_is_empty_or_blank?(phrase)
      phrase.strip.empty?
    end

    def phrase_is_all_caps?(phrase)
      phrase == phrase.upcase
    end

    def phrase_is_a_question?(phrase)
      phrase[-1] == '?'
    end
end
