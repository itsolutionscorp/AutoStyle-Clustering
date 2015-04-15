class Bob
  def hey(interaction_attempt)
    if silence?(interaction_attempt)
      'Fine. Be that way!'
    elsif shouting?(interaction_attempt)
      'Woah, chill out!'
    elsif question?(interaction_attempt)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

    def silence?(txt)
      txt.to_s.gsub(/\s/, '').empty?
    end

    def shouting?(txt)
      txt.upcase == txt
    end

    def question?(txt)
      txt.end_with?('?')
    end

end
