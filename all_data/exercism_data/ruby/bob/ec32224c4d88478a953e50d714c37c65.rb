class Bob
  def hey(param)
    @param = param
    case
      when stating_something
        "Whatever."
      when all_caps
        "Woah, chill out!"
      when asking_something
        "Sure."
      when exclaiming
        "Woah, chill out!"
      when silence
        "Fine. Be that way."
      else
        "Whatever."
    end
  end

  def stating_something
    @param =~ /\.\Z/
  end

  def all_caps
    @param =~ /[AZ]/
  end

  def asking_something
    @param =~ /[[?]\Z]/
  end

  def exclaiming
    @param =~ /[0-9]/ && /[!]\Z/
  end

  def silence
    @param.nil? || @param.empty?
  end

end
