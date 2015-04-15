class WordProblem
  def initialize(sentence)
    @sentence = sentence
  end

  def answer
    raise ArgumentError unless prefix_valid?
    tail.each_slice(2).inject(head) do |result, (operation, operand)|
      result.send(OPERATIONS.fetch(operation) do
        raise ArgumentError
      end, operand.to_i)
    end
  end

  private

  attr_reader :sentence

  def tokens
    @tokens ||= preprocess(*sentence.scan(compound_word_regex))
  end

  def compound_word_regex
    %r{
      what\ is                 |
      multiplied\ by           |
      divided\ by              |
      to\ the\ \d+\w{2}\ power |
      \-?\d+                   |
      \w+
    }xi
  end

  def preprocess(*words)
    words.map do |word|
      if md = word.match(/to the (\d+)\w{2} power/)
        ["exponent", md.captures.first]
      else
        word
      end
    end.flatten
  end

  def prefix
    tokens.first
  end

  def head
    tokens[1].to_i
  end

  def tail
    tokens[2..-1]
  end

  def prefix_valid?
    prefix == "What is"
  end

  OPERATIONS = {
    "plus"          => :+,
    "minus"         => :-,
    "divided by"    => :/,
    "multiplied by" => :*,
    "exponent"      => :**
  }

end
