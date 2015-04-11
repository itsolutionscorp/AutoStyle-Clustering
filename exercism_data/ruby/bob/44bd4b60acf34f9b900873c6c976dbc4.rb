class String
  def no_downcase_chars?
    self.scan(/[a-z]/).count == 0
  end
end

class Bob

  def hey(what)
    return 'Fine. Be that way!' if silence?(what)
    return 'Woah, chill out!' if yell?(what)
    return 'Sure.' if question?(what)
    'Whatever.'
  end

  private

    def yell?(what)
      what.no_downcase_chars?
    end

    def question?(what)
      what.end_with? '?'
    end

    def silence?(what)
      what.strip.empty?
    end

end
