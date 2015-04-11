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
    @commands ||= ordered_command_list
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
  
  def ordered_command_list
    if has_command? "reverse"
      command_list.reverse.drop(1)
    else
      command_list
    end
  end
  
  def command_list
    @command_list ||= COMMAND_LIST.select{|command| has_command? command }
  end
  
  def has_command? command
    !(key & mask(command)).zero?
  end
  
  def mask command
    2**(COMMAND_LIST.index(command) || COMMAND_LIST.length)
  end
end
