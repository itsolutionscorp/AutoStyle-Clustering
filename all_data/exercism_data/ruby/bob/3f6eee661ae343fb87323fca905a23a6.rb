module TeenagePerspective

  def interpret statement
    if statement.strip.empty?
      :being_weird
    elsif statement.upcase == statement
      :hassling_me
    elsif statement.end_with? '?'
      :interrupting_call_of_duty
    else
      :what?
    end
  end

  def respond interpretation
    lackadaisical_responses = {
        :being_weird => 'Fine. Be that way!',
        :hassling_me => 'Woah, chill out!',
        :interrupting_call_of_duty => 'Sure.',
        :what? => 'Whatever.',
    }

    lackadaisical_responses[interpretation]
  end
end

class Bob
  include TeenagePerspective

  def hey message
    respond(interpret message)
  end
end
