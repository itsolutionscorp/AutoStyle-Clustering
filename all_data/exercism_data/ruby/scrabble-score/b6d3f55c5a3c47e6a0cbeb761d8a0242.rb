class Scrabble

  LETTER_VALUE = {'a'=>1,'b'=>3,'c'=>3,'d'=>2,'e'=>1,'f'=>4,'g'=>2,'h'=>4,'i'=>1,'j'=>8,'k'=>5,'l'=>1,'m'=>3,'n'=>1,'o'=>1,'p'=>3,'q'=>10,'r'=>1,'s'=>1,'t'=>1,'u'=>1,'v'=>4,'w'=>4,'x'=>8,'y'=>4,'z'=>10}

  def initialize(word)
    @word = word
  end

  def score
    clean(@word).scan(/./).inject(0) do |score, letter|
      score + LETTER_VALUE[letter]
    end
  end

  def self.score(word)
    Scrabble.new(word).score
  end

  def clean(dirty)
    dirty.nil? ? "" : dirty.downcase.gsub(/[^a-z]/, '')
  end
end
