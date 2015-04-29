# Bob is a lackadaisical teenager
class Bob


  # In conversation, his responses are very limited.
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


  # Conversation
  def hey(bob_hears)

    # Scenario list
    someone_is_silent = bob_hears.split.empty?
    someone_is_yelling = bob_hears === bob_hears.upcase && (bob_hears.count "A-Z") > 0
    someone_asks_a_question = bob_hears.end_with?('?')

    case

      # He answers 'Woah, chill out!' if you yell at him.
      when someone_is_yelling then self.express_being_overwhelmed

      # He says 'Fine. Be that way!' if you address him without actually saying anything.
      when someone_is_silent then self.express_resentment

      # Bob answers 'Sure.' if you ask him a question.
      when someone_asks_a_question then self.express_consent

      # He answers 'Whatever.' to anything else.
      else self.express_indifference

    end

  end

end
