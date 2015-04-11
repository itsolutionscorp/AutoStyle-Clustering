class Bob
  def hey(heard)
    strategies.each_pair do |strategy, response|
      return response if self.send(strategy, heard)
    end
  end
  
  private
  
  def strategies 
    {
    :silence => "Fine. Be that way!",
    :shouting => "Woah, chill out!",
    :question => "Sure.",
    :anything => "Whatever."
    }
  end

  def silence(heard)
    heard.nil? || heard.strip == ""
  end
  
  def shouting(heard)
    heard == heard.upcase
  end
  
  def question(heard)
    heard[-1] == "?"
  end

  def anything(heard)
    true
  end
end
