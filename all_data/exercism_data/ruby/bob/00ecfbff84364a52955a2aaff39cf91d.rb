class Bob
  def hey(sentence)
    sentence = Sentence.new(sentence)
    return 'Fine. Be that way.' if sentence.silence?
    return 'Sure.' if sentence.question?
    return 'Woah, chill out!' if sentence.shouting?
    'Whatever.'
  end

  private

  Sentence = Struct.new(:sentence) do
    def silence?
      sentence.nil? || sentence.empty?
    end

    def question?
      sentence.end_with? '?'
    end

    def shouting?
      sentence == sentence.upcase
    end
  end
end
