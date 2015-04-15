class Bob

  def hey(args)
    silence?(args) || shout?(args)|| question?(args) || default_response
  end

  def silence?(args)
    "Fine, be that way." if args == ""
  end

  def question?(args)
    return 'Sure.' if args[-1] == "?"
  end

  def shout?(args)
    return "Woah, chill out!" if args.upcase == args
  end

  def default_response
    "Whatever."
  end
end
