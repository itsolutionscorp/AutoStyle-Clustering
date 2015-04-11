class Bob

  attr_accessor :whatever, :whoah, :sure, :fine

  def initialize
    @whatever = 'Whatever.'
    @whoah = 'Woah, chill out!'
    @sure = 'Sure.'
    @fine = 'Fine. Be that way!'
  end

  def hey(phrase)
    return fine unless phrase
    parse(phrase)
  end

  private 
  def parse(phrase)
    if they_are_saying_a(phrase)
      whatever 
    elsif they_are_shouting_a(phrase)
      whoah 
    elsif it_sounds_like_a_question(phrase)
      sure 
    else 
      fine
    end 
  end

  def they_are_shouting_a(phrase)
    phrase.match(all_caps_ending_with_exclamation) or 
    phrase.match(all_caps_ending_with_question_mark) or
    phrase.match(all_caps_without_exclamation)
  end

  def they_are_saying_a(phrase)
    phrase.match(mixed_case_ending_with_exclamation) or
    phrase.match(ends_with_period)
  end

  def it_sounds_like_a_question(phrase)
    phrase.match(ends_with_question_mark) or
    more_than_2_sentences_in_the(phrase)
  end

  def ends_with_period
    Regexp.new(/.*\.$/)
  end

  def all_caps_ending_with_exclamation
    Regexp.new(/[A-Z|\s]+[A-Z]!/)
  end

  def ends_with_question_mark
    Regexp.new(/^[A-Z][a-z|\s]+\?$/)
  end

  def mixed_case_ending_with_exclamation
    Regexp.new(/^[A-Z][a-z]+.*!$/)
  end

  def all_caps_ending_with_question_mark
    Regexp.new(/^[A-Z|\s]{2,}\?/)
  end

  def more_than_2_sentences_in_the(phrase)
    phrase.scan(/[A-Z][\w|\s]+[\!|\.|\?]\s*/).length > 2
  end

  def all_caps_without_exclamation
    Regexp.new(/^[A-Z]+\b.*\b[A-Z]+[^!|\?|\.]$/)
  end

end
