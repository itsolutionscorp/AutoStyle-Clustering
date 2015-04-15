class SecretHandshake
  COMMAND_LIST = [
    "wink",
    "double blink",
    "close your eyes",
    "jump",
    "reverse"
  ].freeze
  
  def initialize key
    @key = sanitize key
  end
  
  def commands
    unless @commands
      @commands = COMMAND_LIST.select{|command| command? command }
      @commands = @commands.reverse.drop(1) if command? "reverse"
    end
    @commands
  end
  
  private
  attr_reader :key
  def sanitize key
    if key.instance_of? String
      key.to_i 2
    else
      key.to_i
    end
  end
  
  def command? command
    !(key & 2**(COMMAND_LIST.index(command) || COMMAND_LIST.length)).zero?
  end
end
