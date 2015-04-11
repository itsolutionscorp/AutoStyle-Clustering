class Bob
  def hey(str)
    @str = str
    vocabulary.detect(proc { [0, 'Whatever.'] } ) { |check,response| send(check) }[1]
  end

  private

  attr_accessor :str

  def vocabulary
    { 
      is_yelling?: "Woah, chill out!",
      is_question?: "Sure.",
      is_silence?: 'Fine. Be that way!'
    }
  end

  def is_yelling?
    is_empty? && is_uppercase?
  end

  def is_uppercase?
    str == str.upcase
  end

  def is_empty?
    !str.strip.empty?
  end

  def is_question?
    last_character == '?'
  end

  def last_character
    characters.last
  end

  def characters
    str.split('')
  end

  def is_silence?
    str.strip.empty?
  end
end
