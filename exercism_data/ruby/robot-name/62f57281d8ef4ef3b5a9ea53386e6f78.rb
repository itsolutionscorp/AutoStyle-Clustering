require 'set'

class Robot

  WORD_CHARS = [*('A'..'Z'),*('a'..'z'),*('0'..'9'),'_']
  NUMBER_CHARS = [*('0'..'9')]
  USED_NAMES = Set.new

  def name
    @name ||= Robot.random_name
  end
  
  def reset
    @name = nil
  end

  def self.random_name
    while true do
      name = (WORD_CHARS.sample(2) + NUMBER_CHARS.sample(3)).join
      if USED_NAMES.add?(name) 
        return name
      end
    end
  end
end
