class Bob
  def hey(something)
    case
    when nothing?(something)
      'Fine. Be that way!'
    when /^[^a-z]+$/
      "Woah, chill out!"
    when /\?$/
      'Sure.'
    else
      "Whatever."
    end
  end

  private

  def nothing?(something)
    something == '' || something.nil?
  end
end
