class Beer

      def song(number)
        number_2 = number - 1
        if number == 2
          "#{number} bottles of beer on the wall, #{number} bottles of beer.\nTake one down and pass it around, #{number_2} bottle of beer on the wall.\n"
        elsif number == 0
         "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
       elsif number > 1
        "#{number} bottles of beer on the wall, #{number} bottles of beer.\nTake one down and pass it around, #{number_2} bottles of beer on the wall.\n"
      else
        "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
      end
    end

    def singing(number, next_number)
      if next_number != 0
      number_2 = number - 1
      next_number_2 = next_number - 1
      "#{number} bottles of beer on the wall, #{number} bottles of beer.\nTake one down and pass it around, #{number_2} bottles of beer on the wall.\n\n#{number_2} bottles of beer on the wall, #{number_2} bottles of beer.\nTake one down and pass it around, #{next_number} bottles of beer on the wall.\n\n#{next_number} bottles of beer on the wall, #{next_number} bottles of beer.\nTake one down and pass it around, #{next_number_2} bottles of beer on the wall.\n\n"
      elsif next_number == 0 && number == 3
       "3 bottles of beer on the wall, 3 bottles of beer.\nTake one down and pass it around, 2 bottles of beer on the wall.\n\n2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n\n1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n\nNo more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n\n"
      end
    end

    def verse(number)
      return song(number)
    end

    def sing(number, next_number = 0)
      return singing(number, next_number)
    end
end
