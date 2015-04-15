class Scrabble
  def initialize(letters)
    @letters = letters
  end

  def score
    score = 0
    if @letters != nil && @letters != @letters.upcase
      letter_array = @letters.split(//)
      letter_array.each do |letter|
        score = score+1 if letter.upcase == ("A" ||"E"|| "I"|| "O"|| "U"|| "L"|| "N"|| "R"|| "S"||"T")
        score = score+2 if letter.upcase == ("D" ||"G")
        score = score+3 if letter.upcase == ("B" ||"C"|| "M"|| "P")
        score = score+4 if letter.upcase == ("F" ||"H"|| "V"|| "W"|| "Y")
        score = score+5 if letter.upcase == ("K")
        score = score+8 if letter.upcase == ("J" ||"X")
        score = score+10 if letter.upcase == ("Q"||"Z")
      end
    end
    score
  end
end
