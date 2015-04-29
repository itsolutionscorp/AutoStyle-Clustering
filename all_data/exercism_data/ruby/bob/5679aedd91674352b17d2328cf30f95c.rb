class Bob
  def initialize
    @answers = {
      asking:   'Sure.',
      fine:     'Fine. Be that way!',
      shouting: 'Woah, chill out!',
      whatever: 'Whatever.'
    }
  end

  def hey(input)

    phrase = Phrase.new(input)

    return @answers[:fine] if phrase.silent?
    return @answers[:shouting] if phrase.yelling?
    return @answers[:asking] if phrase.questioning?
    @answers[:whatever]
  end
end

class Phrase
  attr_reader :statement

  def initialize(input)
    @statement = input
  end

  def silent?
    statement !~ /[^[:space:]]/
  end

  def yelling?
    statement == statement.upcase && !(statement.chomp('?') =~ /[0-9, ]+/)
  end

  def questioning?
    statement.end_with?('?')
  end
end
