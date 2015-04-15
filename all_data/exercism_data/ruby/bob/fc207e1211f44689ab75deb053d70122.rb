class Phrase

  def initialize(input)
    @phrase = input.strip.gsub(/\d/, "")
  end

  def blank?
    @phrase.empty?
  end

  def shout?
    p = @phrase.gsub(/\d/, "")
    p != p.downcase && p == p.upcase
  end

  def question?
    @phrase =~ /\?\Z/m
  end

end

class Bob

  DEFAULT_ANSWER = "Whatever."
  SHOUT_ANSWER = "Woah, chill out!"
  QUESTION_ANSWER = "Sure."
  SILENCE_ANSWER = "Fine. Be that way!"

  def hey(phrase)
    case Phrase.new(phrase)
      when :blank?.to_proc    then SILENCE_ANSWER
      when :shout?.to_proc    then SHOUT_ANSWER
      when :question?.to_proc then  QUESTION_ANSWER
      else
        DEFAULT_ANSWER
    end
  end

end
