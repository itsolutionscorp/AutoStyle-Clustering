class Yelling < Struct.new(:sentence)
  def test?
    !sentence.strip.empty? && !letters.empty? && letters.upcase == letters
  end

  def answer
    'Woah, chill out!'
  end

  private

  def letters
    sentence.gsub(/[^a-zA-Z]+/, '')
  end
end

class Staring < Struct.new(:sentence)
  def test?
    sentence.strip.empty?
  end

  def answer
    'Fine. Be that way!'
  end
end

class Question < Struct.new(:sentence)
  def test?
    !yelling? && sentence.strip[-1] == '?'
  end

  def answer
    'Sure.'
  end

  private

  def yelling?
    Yelling.new(sentence).test?
  end
end

class EveryThingElse < Struct.new(:sentence)
  def test?
    true
  end

  def answer
    'Whatever.'
  end
end

class Bob

  def hey(sentence)
    types_of_sentence.each do |klass|
      type = klass.new(sentence)
      return type.answer if type.test?
    end
  end

  private

  def types_of_sentence
    [Yelling, Staring, Question, EveryThingElse]
  end

end
