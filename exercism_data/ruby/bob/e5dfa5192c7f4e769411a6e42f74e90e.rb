class Bob
  def hey(noise)

    def noise.shouting?
      self.upcase == self
    end

    def noise.question?
      self =~ /\?$/
    end

    def noise.silence?
      (self =~ /^\s*$/ || self.nil?)
    end

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
