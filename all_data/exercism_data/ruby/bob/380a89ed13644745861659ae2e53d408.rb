class Bob
  def initialize
    @is_upper = ->(x) { !x[/[[:lower:]]/] }
    @only_digits = ->(x) { !x[/[[:alpha:]]/]} 
  end

  def shouting?(phrase)
    @is_upper.(phrase) && !@only_digits.(phrase)
  end

  def questioning?(phrase)
    (!@is_upper.(phrase) || @only_digits.(phrase)) && phrase[-1] == '?'
  end

  def hey(phrase)
    if shouting?(phrase)
      'Whoa, chill out!'
    elsif questioning?(phrase)
      'Sure.'
    elsif phrase.gsub(/\s/, '').empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
