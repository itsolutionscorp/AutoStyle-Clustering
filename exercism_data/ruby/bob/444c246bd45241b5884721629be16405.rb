class Bob
  def hey(string)
    whatever = 'Whatever.'
    woah = 'Woah, chill out!'
    sure = 'Sure.'
    fine = 'Fine. Be that way!'
    i = 0
    string.each_char do |c|
      if c == ' '
        i += 1
      end
    end

    numbers = true
    string.each_char do |n|
      if n != ' ' && n != ',' && n.to_i.to_s != n && n != '?'
        numbers = false
      end
    end


    if string.length == i
      fine
    elsif string.upcase == string && !numbers
      woah
    elsif string[-1] == '?'
      sure
    else
      whatever
    end
  end

end
