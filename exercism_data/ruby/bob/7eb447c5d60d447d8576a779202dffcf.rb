class Bob
  def hey(message)
    return 'Fine. Be that way!' if blank? message
    return 'Woah, chill out!' if all_caps? message
    return 'Sure.' if question? message
    "Whatever."
  end

  private
    def all_caps?(message)
      !message[/[[:lower:]]/] 
    end

    def question?(message)
      message[/\?\z/]
    end

    def blank?(message)
      message.gsub(/ +/, '').empty?
    end
end
