class Scrabble
  def initialize(letters)
    @letters = letters
  end

  def score
    score = 0
    if @letters != nil && @letters != @letters.upcase
      letter_array = @letters.split(//)
      letter_array.each do |letter|
        case letter
          when "A","E","I","O","U","L","N","R","S","T"
            score = score+1
          when "D","G"
            score = score+2
          when "B","C","M","P"
            score = score+3
          when "F","H","V","W","Y"
            score = score+4
          when "K"
            score = score+5
          when "J","X"
            score = score+8
          when "Q","Z"
            score = score+10
        end
      end
    end
    score
  end
end
