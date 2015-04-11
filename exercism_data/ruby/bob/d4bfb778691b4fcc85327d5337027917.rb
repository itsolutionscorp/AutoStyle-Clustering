class Request
  attr_accessor :sentence
  def initialize(sentence)
    self.sentence = sentence
  end

  def to_s
    sentence
  end

  def silence?
    sentence.nil? || sentence == ""
  end

  def yelling?
    sentence == sentence.upcase
  end

  def question?
    sentence =~ /\?$/ 
  end
end

class Bob
  def hey(sentence)
    respond_to Request.new(sentence)
  end

  private

    def respond_to(request)
      case 
      when request.silence?  then "Fine. Be that way."
      when request.yelling?  then "Woah, chill out!"
      when request.question? then "Sure."
      else "Whatever."
      end
    end
end
