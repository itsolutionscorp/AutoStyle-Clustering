class Bob
  def hey(smth)
    smth.strip!
    return "Fine. Be that way!" if smth == ''
    return "Woah, chill out!"   if smth == smth.upcase && smth != smth.downcase
    return "Sure."              if smth[-1] == '?'
    "Whatever."
  end
end
