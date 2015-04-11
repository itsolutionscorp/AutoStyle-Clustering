class Bob
  def hey(greet)
    @greet = greet
    if    yell?     then 'Woah, chill out!'
    elsif question? then 'Sure.'
    elsif silence?  then 'Fine. Be that way!'
    else                 'Whatever.'
    end
  end

  private

  def yell?
    @greet =~ /[A-Z]/ && @greet !~ /[a-z]/
  end

  def question?
    @greet =~ /.*\?\z/
  end

  def silence?
    @greet =~ /\A\s*\z/
  end
end
