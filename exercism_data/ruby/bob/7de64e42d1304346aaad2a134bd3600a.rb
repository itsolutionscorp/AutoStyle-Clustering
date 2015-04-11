class Bob
  def hey(string) 
    woah = 'Woah, chill out!' 
    sure = 'Sure.' 
    fine = 'Fine. Be that way!' 
    whatever = 'Whatever.'
    
    i = 0
    string.each_char do |c|
      if c == ' '
        i += 1
      end
    end


    total = true
    string.each_char do |n|
      if n != '?' && n != ' ' && n.to_i.to_s != n  && n != ',' 
        total = false
      end
    end

    woa = string.upcase == string
    if string.length == i
      fine
    elsif woa && !total
      woah
    elsif string[-1] == '?'
      sure
    else
      whatever
    end
  end
end
