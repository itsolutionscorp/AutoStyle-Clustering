class Bob
  def hey(say)
    say = gets.chomp
    if say == say.upcase
      return "Woah, chill out!"
    end
  end
end
