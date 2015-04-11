#
# Author: Carol Dawson
#
# This class provides a solution to the exercism.io Bob exercise
# The aim to simulate the responses of a lackadaisical teenager: Bob
#
# His responses should be the following:
#
# Bob answers 'Sure.' if you ask him a question.
# He answers 'Woah, chill out!' if you yell at him (ALL CAPS).
# He says 'Fine. Be that way!' if you address him without actually saying anything.
# He answers 'Whatever.' to anything else.
#
# Use:
# teenager = Bob.new
# teenager.hey('WATCH OUT!')
#  -> "Woah, chill out!"
#
class Bob

  def hey(communication)
    if communication.strip.length == 0
      "Fine. Be that way!"
    elsif communication.match /([A-Z])/ and not communication.match /([a-z])/
      "Woah, chill out!"
    elsif communication.end_with? "?"
      "Sure."
    else
      "Whatever."
    end
  end

end
