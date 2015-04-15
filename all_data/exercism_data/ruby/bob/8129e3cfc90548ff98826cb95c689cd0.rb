class Bob
  def hey phrase 
    return 'Fine. Be that way!' if is_empty? phrase 
    return 'Woah, chill out!' if is_shouting? phrase
    return 'Sure.' if is_a_question? phrase
    'Whatever.'
  end

  private
    def is_empty? phrase
      phrase.nil? || phrase.strip.empty?
    end

    def is_shouting? phrase
      phrase == phrase.upcase
    end

    def is_a_question? phrase
      phrase.end_with?('?')
    end 
end
