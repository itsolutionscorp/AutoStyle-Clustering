class Scrabble

  def initialize(word)
    if word == nil
      @word = word.to_a
    else
      @word = word.downcase.split("") 		
    end
  end

  def self.score(word)
    word = word.split("")
    if word.length == 0
      0
    else
      score = 0
      word.each do |letter|
        if letter =~ /[aeiourstln]/
          score += 1
        elsif letter =~ /[dg]/
          score += 2
        elsif letter =~ /[bcmp]/
          score += 3
        elsif letter =~ /[fhvwy]/
          score += 4
        elsif letter =~ /[k]/
          score += 5
        elsif letter =~ /[jx]/
          score += 8
        elsif letter =~ /[qz]/
          score += 10
        end
      end
      score
    end
  end

  def score
    if word.length == 0
      0
    else
      score = 0
      word.each do |letter|
        if letter =~ /[aeiourstln]/
          score += 1
        elsif letter =~ /[dg]/
          score += 2
        elsif letter =~ /[bcmp]/
          score += 3
        elsif letter =~ /[fhvwy]/
          score += 4
        elsif letter =~ /[k]/
          score += 5
        elsif letter =~ /[jx]/
          score += 8
        elsif letter =~ /[qz]/
          score += 10
        end
      end
      score
    end
end
