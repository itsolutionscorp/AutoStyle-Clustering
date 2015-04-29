class Bob
  def hey(bob_hears)
    if bob_hears.strip.empty?
      self.express_resentment
    elsif bob_hears === bob_hears.upcase && (bob_hears.count "A-Z") > 0
      self.express_being_overwhelmed
    elsif bob_hears.end_with?('?')
      self.express_consent
    else
      self.express_indifference
    end
  end

  def express_resentment
   'Fine. Be that way!'
  end

  def express_consent
    'Sure.'
  end

  def express_indifference
    'Whatever.'
  end

  def express_being_overwhelmed 
   'Woah, chill out!'
  end 
end
