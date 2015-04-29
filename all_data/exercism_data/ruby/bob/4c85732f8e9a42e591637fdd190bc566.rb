class Bob
  def hey(message)
    [:silence,
     :vociferation,
     :interrogation,
     :confabulation].each_with_index do |stimuli, response|
       return protagonist(response) if !!(send(stimuli, message))
    end
  end

  def protagonist(response)
    send([:recalcitrant, :remonstratory, :cooperative, :defiant][response])
  end

  private

  def silence(message)
    message.strip == ""
  end

  def vociferation(message)
    message == message.upcase
  end

  def interrogation(message)
    !!(message.gsub("\n", "") =~ /\?$/)
  end

  def confabulation(message)
    true
  end

  def recalcitrant
    "Fine. Be that way!"
  end

  def remonstratory
    "Woah, chill out!"
  end

  def cooperative
    "Sure."
  end

  def defiant
    "Whatever."
  end
end
