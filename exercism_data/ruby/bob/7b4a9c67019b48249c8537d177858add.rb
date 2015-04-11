class Bob
  def hey(dialogue)
    stripped_dialog = dialogue.gsub(/\n/, '')
    case 
    when shouting?(stripped_dialog)
      "Woah, chill out!"
    when question?(stripped_dialog)
      'Sure.'
    when silence?(stripped_dialog)
      'Fine. Be that way!'
    else
      "Whatever."
    end
  end

  private

  def silence?(stripped_dialog)
    stripped_dialog =~ /^\s*$/
  end

  def question?(stripped_dialog)
    stripped_dialog[-1] == '?'
  end

  def shouting?(stripped_dialog) 
    stripped_dialog == stripped_dialog.upcase && stripped_dialog != stripped_dialog.downcase
  end

end
