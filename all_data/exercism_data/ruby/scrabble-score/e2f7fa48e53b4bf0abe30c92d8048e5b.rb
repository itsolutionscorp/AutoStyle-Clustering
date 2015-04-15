class Scrabble
  def initialize(word)
    @word = word
  end

  def score
    score = 0
    @word.nil? ? word_array = [""] : word_array = @word.split("")
    word_array.each do |letter|
      case letter
        when /a|e|i|o|u|l|n|r|s|t/i then score  += 1
        when /d|g/i then score += 2
        when /b|c|m|p/i then score +=3
        when /f|h|v|w|y/i then score +=4
        when /k/i then score += 5
        when /j|x/i then score += 8
        when /q|z/i then score += 10
      end
    end
    score
  end

  def self.score(word)
    self.new(word).score
  end
end
