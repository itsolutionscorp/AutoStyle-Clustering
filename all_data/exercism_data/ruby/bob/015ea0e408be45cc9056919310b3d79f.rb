class Bob
  def hey(arg)
    silence = true
    arg.each_char do |x|
      if x != " "
        silence = false
      end
    end

    numbers = true
      arg.each_char do |y|
        if y != "," && y != " " && y.to_i.to_s != y && y != "?"
          numbers = false
        end
      end

    case
      when arg.empty? || silence
        "Fine. Be that way!"
      when arg == arg.upcase && !numbers
        "Woah, chill out!"
      when arg[-1] == "?"
        "Sure."
      else
        "Whatever."
    end
  end
end
