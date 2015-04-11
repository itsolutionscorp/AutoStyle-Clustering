class Bob
  attr_accessor :sentence

  def hey(some_words)
    @sentence = some_words

    case
    when nothing_is_said   then 'Fine. Be that way!'
    when shouting_at_me    then 'Woah, chill out!'
    when question_is_asked then 'Sure.'
    else 'Whatever.'
    end
  end

  private

    def nothing_is_said
      sentence.nil? || sentence.empty?
    end

    def shouting_at_me
      sentence.eql? sentence.upcase
    end

    def question_is_asked
      sentence.match(/\?$/)
    end

end
