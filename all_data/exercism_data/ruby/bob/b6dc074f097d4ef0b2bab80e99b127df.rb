class Bob
  attr_reader :sentence_typer

  def initialize
    @sentence_type = SentenceType.new
  end

  def hey(input)
    if @sentence_type.is_only_spaces? input
      'Fine. Be that way!'
    elsif @sentence_type.all_letters_are_caps? input
      'Woah, chill out!'
    elsif @sentence_type.last_char_is_question_mark? input
      'Sure.'
    else
      'Whatever.'
    end
  end

  class SentenceType

    def is_only_spaces?(input)
      input.strip.empty?
    end

    def all_letters_are_caps?(input)
      input.upcase == input
    end

    def last_char_is_question_mark?(input)
      input.end_with?("?")
    end
  end

end
