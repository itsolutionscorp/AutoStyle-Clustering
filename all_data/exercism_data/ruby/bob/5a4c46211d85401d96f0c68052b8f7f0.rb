class Bob
  def hey(bob_hears)
   case
    when bob_hears.split.empty? then self.express_resentment
    when bob_hears === bob_hears.upcase && (bob_hears.count "A-Z") > 0 then self.express_being_overwhelmed
    when bob_hears.end_with?('?') then self.express_consent
    else self.express_indifference
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
