class Bob

  def hey(value)
    case
      when is_shouting?(value)
        'Woah, chill out!'
      when is_a_question?(value)
        'Sure.'
      when is_silence?(value)
         'Fine. Be that way!'
      else
        'Whatever.'
    end

  end

  private
  def is_shouting?(value)
    return unless has_alpha_characters?(value)
    value.upcase == value
  end

  private
  def is_a_question?(value)
    value.respond_to?(:match) && value.match(/\?\Z/)
  end

  private
  def is_silence?(value)
    value.respond_to?(:gsub) && value.gsub(/\s/, '').empty?
  end

  private
  def has_alpha_characters?(value)
    value.respond_to?(:match) && value.match(/[a-zA-Z]/)
  end

end
