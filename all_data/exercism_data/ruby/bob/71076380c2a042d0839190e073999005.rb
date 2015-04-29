class Bob
  RESPONSES = {
    yelling: "Woah, chill out!",
    question: "Sure.",
    empty: "Fine. Be that way!"
  }

  def hey(greeting)
    mood = Greeting.new(greeting).mood
    RESPONSES[mood] || "Whatever."
  end

end

class Greeting
  def initialize(phrase)
    @phrase = phrase
  end

  def mood
    if is_yelling?
      :yelling
    elsif is_question?
      :question
    elsif is_empty?
      :empty
    end
  end

  def is_yelling?
    @phrase == @phrase.upcase && @phrase.match(/[a-zA-Z]/)
  end

  def is_question?
    @phrase.end_with? "?"
  end

  def is_empty?
    @phrase.strip.empty?
  end
end
