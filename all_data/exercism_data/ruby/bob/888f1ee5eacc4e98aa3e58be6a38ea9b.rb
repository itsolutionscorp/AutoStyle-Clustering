class Bob
  attr_reader :interrogation

  def hey(said)
    @interrogation = said.chomp

    response 
  end

  def evaluate(string)
    if !(/[A-Za-z]/.match string)
      :silence
    elsif /[A-Z]{3}+/.match string
      :shout
    elsif/.\?$/.match string
      :question
    else
      :other
    end
  end

  def response
    responding_to = evaluate(interrogation)

    if responding_to == :silence
      'Fine. Be that way!'
    elsif responding_to == :shout
      'Whoah, chill out!'
    elsif responding_to == :question
      'Sure.'
    else 
      'Whatever.'
    end
  end

end
