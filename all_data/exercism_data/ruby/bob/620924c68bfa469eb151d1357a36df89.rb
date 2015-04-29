class Teenager

  def hey(input='')
    interpret Message.new(input.to_s)
  end

private

  def interpret(message)
    case message
    when :statement?;   'Fine. Be that way!'
    when :exclamation?; 'Woah, chill out!'
    when :question?;    'Sure.'
    else                'Whatever.'
    end
  end

  Message = Struct.new :content do
    def statement?;   content.empty?; end
    def exclamation?; content == content.upcase; end
    def question?;    content.end_with?('?'); end
  end

end

class Symbol
  def ===(object)
    super(object) || (object.respond_to? self and object.send self)
  end
end

Bob = Class.new Teenager
