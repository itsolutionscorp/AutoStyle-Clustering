class Comment
  RESPONSES = {
    fine: 'Fine. Be that way!',
    woah: 'Woah, chill out!',
    sure: 'Sure.',
    whatever: 'Whatever.'
  }

  attr_accessor :comment

  def initialize(str)
    @comment = str
  end

  def appropriate_response
    response_key = case
    when shouting?
      :woah
    when looks_like_a_question?
      :sure
    when empty?
      :fine
    else
      :whatever
    end
    RESPONSES[response_key]
  end

  def looks_like_a_question?
    comment =~ /\?$/ && not_too_long?
  end

  def not_too_long?
    comment !~ /\n/
  end

  def shouting?
    !empty? && !all_numbers? && comment == comment.upcase
  end

  def all_numbers?
    comment !~ /[[:alpha:]]/
  end

  def empty?
    comment =~ /\A\s*\Z/
  end
end

class Bob
  def hey(comment)
    Comment.new(comment).appropriate_response
  end
end
