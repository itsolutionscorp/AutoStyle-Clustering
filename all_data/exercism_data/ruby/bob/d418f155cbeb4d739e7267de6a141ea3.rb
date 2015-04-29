class Bob
  def hey(incoming_noise)
    noise = Noise.new(incoming_noise)
    if noise.silence?
      "Fine. Be that way!"
    elsif noise.shouting?
      'Woah, chill out!'
    elsif noise.question?
      'Sure.'
    else
      "Whatever."
    end
  end
end

class Noise
  attr_reader :noise
  def initialize(noise)
    @noise = noise
  end

  def shouting?
    noise.upcase == noise
  end

  def question?
    noise.end_with?("?")
  end

  def silence?
    noise.nil? || noise =~ /^\s*$/
  end
end
