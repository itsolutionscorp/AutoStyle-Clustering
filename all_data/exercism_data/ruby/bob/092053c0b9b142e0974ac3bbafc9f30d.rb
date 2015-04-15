class Bob

  def hey(address)
    @address = address

    case
      when is_yell? && has_alpha?
        return "Woah, chill out!"
      when is_question?
        return "Sure."
      when is_blank?
        return "Fine. Be that way!"
      else
        return "Whatever."
    end
  end

  private

  def is_yell?
    @address.upcase!.nil?
  end

  def has_alpha?
    @address.match(/[[:alpha:]]/) != nil
  end

  def is_question?
    @address[-1] == '?'
  end

  def is_blank?
    @address.strip.empty?
  end
end
