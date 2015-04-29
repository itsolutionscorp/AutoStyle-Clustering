class Bob

  def hey(_)
    answer_to Sentence.new(_).sentence_type
  end

  private
    def answer_to(sentence_type)
      known_answers[sentence_type]
    end

    def known_answers
      { nothing:  "Fine. Be that way!",
        shouting: "Woah, chill out!",
        question: "Sure.",
        unknown:  "Whatever." }
    end
end

class Sentence
  attr_reader :sentence

  def initialize(sentence)
    @sentence = sentence
  end

  def sentence_type
    return :nothing  if nothing?
    return :shouting if shouting?
    return :question if question?
    :unknown
  end

  private
    def nothing?
      sentence.nil? || sentence.empty?
    end

    def shouting?
      sentence.eql? sentence.upcase
    end

    def question?
      sentence.match(/\?$/)
    end
end
