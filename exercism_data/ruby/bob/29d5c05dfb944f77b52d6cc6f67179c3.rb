class Bob
  def hey(saytobob)
    case
      when saytobob == saytobob.upcase && saytobob =~ /[A-Z]/ then "Woah, chill out!"
      when saytobob[-1] == "?" then "Sure."
      when saytobob =~ /\A\ *\z/ then "Fine. Be that way!"
      else "Whatever."
    end
  end

end

# && saytobob[-1] != "?\n"
