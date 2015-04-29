class Message  
  attr_reader :message
  
  def initialize(message)
    @message = message
  end
  
  def empty?
    @empty = !message || message.strip.empty? if @empty.nil?
    @empty
  end
  
  def yelling?
    !empty? && (message.gsub(/[^A-Za-z]/, '') =~ /\A[A-Z]+\z/) != nil
  end
  
  def question?
    !empty? && !yelling? && message.end_with?('?')
  end
  
  def statement?
    !empty? && !yelling? && !question?
  end
end

class Bob
  RESPONSES = {
    lambda{|msg| msg.question?} => 'Sure.',
    lambda{|msg| msg.yelling?} => 'Woah, chill out!',
    lambda{|msg| msg.statement?} => 'Whatever.',
    lambda{|msg| msg.empty?} => 'Fine. Be that way!'
  }
  def hey(message)
    msg = Message.new(message)
    RESPONSES.find {|l, lmsg| l.call(msg) }[1]
  end
end
