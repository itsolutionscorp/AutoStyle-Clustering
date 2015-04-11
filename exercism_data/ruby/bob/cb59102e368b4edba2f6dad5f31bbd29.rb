class Bob
  def hey(something_said)
  	respond_with she_said
  end

  private

  def respond_with something_said
  	begin
  	  return 'Fine. Be that way!' if silent? something_said
  	  return 'Woah, chill out!' if yelling? something_said
  	  return 'Sure.' if question? something_said
  	  raise
  	rescue
  	  return "Whatever."
  	end
  end

  def question? she_said
  	something_said.strip.empty?
  end

  def silent? she_said
  	something_said.strip.empty?
  end

  def yelling? she_said
  	something_said.upcase === she_said
  end
end
