class Bob

  def hey(args)
    silence?(args) || shout?(args)|| question?(args) || default_response
  end

private
  def silence?(args)
    'Fine, be that way.' if args.empty?
  end

  def question?(args)
    'Sure.' if args.end_with?('?')
  end

  def shout?(args)
    'Woah, chill out!' if args.upcase == args
  end

  def default_response
    'Whatever.'
  end
end
