class Bob

  FINE = 'Fine. Be that way.'
  WOAH = 'Woah, chill out!'
  SURE = 'Sure.'
  WHATEVER = 'Whatever.'

  def hey(s)
    comment = Comment.new(s)
    return FINE if comment.quiet?
    return WOAH if comment.yell?
    return SURE if comment.ask?
    return WHATEVER
  end

  class Comment
    def initialize(s)
      @s = s
    end

    def quiet?
      @s.nil? or @s.empty?
    end

    def yell?
      @s.upcase == @s
    end

    def ask?
      @s.end_with?('?')
    end
  end

end
