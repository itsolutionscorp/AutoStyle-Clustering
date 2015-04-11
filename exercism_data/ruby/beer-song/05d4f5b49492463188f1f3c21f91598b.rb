class Beer

  require "active_support/core_ext/string"

  def verse n
    <<-VERSE.strip_heredoc
      #{n == 0 ? 'No more' : n} bottle#{'s' if n != 1} of beer on the wall, #{n == 0 ? 'no more' : n} bottle#{'s' if n != 1} of beer.
      #{n > 0 \
        ? "Take #{n > 1 ? 'one' : 'it'} down and pass it around, #{n > 1 ? n-1 : 'no more' } bottle#{n == 2 ? '' : 's'} of beer on the wall."
        : "Go to the store and buy some more, 99 bottles of beer on the wall."
      }
    VERSE
  end

  def sing upperbound, lowerbound = 0
    upperbound.downto(lowerbound).each_with_object("") { |n, result| result += verse(n) + "\n" }
  end

end
