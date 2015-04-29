require 'delegate'

class Bob
  def hey(sentence)
    sentence = Sentence.new(sentence)

    if sentence.empty?
      'Fine. Be that way!'
    elsif sentence.yelling?
      'Woah, chill out!'
    elsif sentence.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  class Sentence < SimpleDelegator
    def initialize(sentence)
      super sentence.to_s
    end

    def empty?
      __getobj__.strip.empty?
    end

    def yelling?
      __getobj__ == __getobj__.upcase
    end

    def question?
      __getobj__.end_with? '?'
    end
  end
end
