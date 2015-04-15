module Sentence
  def self.type_of sentence
    if sentence.to_s.empty?
      :silence
    elsif sentence == sentence.upcase
      :yelling
    elsif sentence.end_with? '?'
      :question
    end
  end
end

class Person
  def hey sentence
    answer_to[Sentence.type_of(sentence)]
  end

  private

  def answer_to
    @answer_to ||= {}
  end
end

class Bob < Person
  def initialize
    answer_to.default    = "Whatever."

    answer_to[:silence]  = "Fine. Be that way!"
    answer_to[:yelling]  = "Woah, chill out!"
    answer_to[:question] = "Sure."
  end
end
