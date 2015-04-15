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
  	!input.empty? && input.ends_with?("?")
  end
  
  def self.l33t?(input)
    if !input.empty? && input.starts_with?("Bro,")
      input.gsub('a','4')
      input.gsub('e','3')
      input.gsub('i','1')
      input.gsub('o','0')
      input.split.each do |word|
        if word.starts_with?(/^[QWRTPSDFGHJKLZXVBNMY]/)
          word[0] = word[0].downcase
          
        end

    end
  end
  
  def self.statement?(input)
  	!input.empty?
  end
end
