class Bob

  Responses = [
               lambda {|x| "Fine. Be that way!" if  x.strip.empty? },
               lambda {|x| "Woah, chill out!"   if x.upcase == x  },
               lambda {|x| "Sure."              if  x[-1] == "?" },
               lambda {|x| "Whatever." }
              ]

  def hey(msg)
    Responses.inject(nil) {|a,n| a || n.call(msg)}
  end

end
