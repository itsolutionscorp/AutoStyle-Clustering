##
# Teach a teen to fish...
#
class Response
  class << self
    attr_accessor :all

    def inherited(klass)
      self.all ||= []
      self.all << klass.new
    end

    def what?(&blk)
      define_method(:accept?) {|msg| blk.(msg) }
    end

    def oh_yeah_well(msg)
      define_method(:response) { msg }
    end
  end
end

##
# Sugar, sugar.
#
def given(lip, &blk)
  Class.new(Response, &blk)
end

# Class is in session
################################################################################

given :silence do
  what? {|msg| msg.nil? or msg == '' }
  oh_yeah_well 'Fine. Be that way.'
end

given :shouting do
  what? {|msg| msg == msg.upcase }
  oh_yeah_well 'Woah, chill out!'
end

given :question do
  what? {|msg| msg =~ /\?\z/ }
  oh_yeah_well 'Sure.'
end

given :whatever do
  what? {|msg| true }
  oh_yeah_well 'Whatever.'
end

##
# Use what you have learned
#
class Bob
  def hey(msg)
    Response.all.each do |r|
      return r.response if r.accept? msg
    end
  end
end
