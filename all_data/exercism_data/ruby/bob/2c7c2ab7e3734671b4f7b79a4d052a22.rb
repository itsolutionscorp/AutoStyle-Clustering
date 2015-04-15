class Bob
  NOT_A_LETTER = /[^A-Za-z]/
  ALL_CAPS = /\A[A-Z]+\Z/

  def hey(greeting)
    @greeting = greeting

    if empty_greeting?
      'Fine. Be that way!'
    elsif all_caps?
      'Woah, chill out!'
    elsif ends_with_question_mark?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def empty_greeting?
    @greeting.strip.empty?
  end

  def all_caps?
    @greeting.gsub(NOT_A_LETTER, '') =~ ALL_CAPS
  end

  def ends_with_question_mark?
    @greeting.end_with?('?')
  end
end
