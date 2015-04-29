class Beer

  require "active_support/core_ext/string"

  def verse n
    <<-VERSE.strip_heredoc
      #{number_of_bottles(n).capitalize} of beer on the wall, #{number_of_bottles(n)} of beer.
      #{case n
        when 0
          "Go to the store and buy some more, 99 bottles of beer on the wall."        
        when 1
          "Take it down and pass it around, #{number_of_bottles(n-1)} of beer on the wall."
        else
          "Take one down and pass it around, #{number_of_bottles(n-1)} of beer on the wall."
        end
      }
    VERSE
  end

  def sing upperbound, lowerbound = 0
    upperbound.downto(lowerbound).each_with_object("") { |n, result| result << verse(n) + "\n" }
  end

  private

  def number_of_bottles n
    case n
    when 0
      'no more bottles'
    when 1
      '1 bottle'
    else
      "#{n} bottles"
    end   
  end

end
