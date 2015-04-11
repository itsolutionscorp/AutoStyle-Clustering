class Bob
  def self.response(input)
    if self.shouting?(input)
      return 'Woah, chill out!'
    elsif self.question?(input)
      return 'Sure.'
    elsif self.statement?(input)
      return 'Whatever.'
    else
      return 'Fine. Be that way!'
    end
  end

  def self.shouting?(input)
    !input.empty? && input == input.upcase
  end

  def self.question?(input)
  	!input.empty? && input.include?("?")
  end

  def self.statement?(input)
  	!input.empty?
  end
end
