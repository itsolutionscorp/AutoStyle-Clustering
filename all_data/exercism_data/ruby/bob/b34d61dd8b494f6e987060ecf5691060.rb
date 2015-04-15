class Bob
  def hey(phrase_init)
    if (phrase_init.count " ") > 2 && (phrase_init.count "^ ") < 1 || phrase_init.empty?
       'Fine. Be that way!'
    elsif (phrase_init === phrase_init.upcase && (phrase_init.count "0-9") < 1) || ((phrase_init.count "0-9") > 0 && phrase_init.end_with?('!'))
       'Woah, chill out!'
    elsif phrase_init.end_with?('?') || ((phrase_init.count "0-9") > 0 && phrase_init.end_with?('?'))
       'Sure.'
    elsif phrase_init != phrase_init.nil?
       'Whatever.'
    end
  end
end
