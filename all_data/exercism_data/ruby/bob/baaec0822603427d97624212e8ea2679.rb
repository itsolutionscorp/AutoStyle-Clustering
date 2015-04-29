class Bob
  def hey *args
    if args.count == 1
      arg = args[0]
      return 'Fine. Be that way!' if is_blank?(arg)
      return 'Woah, chill out!' if is_uppercase?(arg)
      return 'Sure.' if is_question?(arg)
    end
    'Whatever.'
  end

  private
  def is_blank?(string)
    string !~ /[^[:space:]]/
  end

  def is_uppercase?(string)
    string.upcase == string
  end

  def is_question?(string)
    string =~ /^*\?+$/
  end
end
