class Bob
  def hey(str)
    @stimulus = Stimulus.new(str)
    vocabulary.detect(proc { [0, 'Whatever.'] } ) { |check,response| send(check) }[1]
  end

  private

  attr_reader :stimulus

  def vocabulary
    {
      yelling?: "Woah, chill out!",
      question?: "Sure.",
      silence?: 'Fine. Be that way!'
    }
  end

  def yelling?
    not_empty? && uppercase?
  end

  def not_empty?
    !silence?
  end

  def question?
    stimulus.last_character == '?'
  end

  def silence?
    stimulus.trimmed_str.empty?
  end

  def uppercase?
    stimulus.uppercase?
  end
end

class Stimulus
  def initialize(str)
    @str = str
  end

  def uppercase?
    @str == uppercase_str
  end

  def last_character
    characters.last
  end

  def trimmed_str
    @str.strip
  end

  private

  attr_reader :str

  def uppercase_str
    @str.upcase
  end

  def characters
    @str.split('')
  end
end
